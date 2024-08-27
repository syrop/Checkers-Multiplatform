package pl.org.seva.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module
import pl.org.seva.checkers.data.datasource.PiecesDataSource
import pl.org.seva.checkers.data.mapper.PiecesDataToDomainMapper
import pl.org.seva.checkers.data.mapper.PiecesDomainToDataMapper
import pl.org.seva.checkers.data.repository.PiecesLiveRepository
import pl.org.seva.checkers.domain.repository.PiecesRepository
import pl.org.seva.checkers.domain.usecase.BlackMoveUseCase
import pl.org.seva.checkers.domain.usecase.FetchPiecesUseCase
import pl.org.seva.checkers.domain.usecase.ResetUseCase
import pl.org.seva.checkers.domain.usecase.WhiteMoveUseCase
import pl.org.seva.checkers.presentation.mapper.PiecesDomainToPresentationMapper
import pl.org.seva.checkers.presentation.mapper.PiecesPresentationToDomainMapper

val presentationModule = module {
    single {
        PiecesLiveRepository(
            inject<PiecesDataSource>().value,
            inject<PiecesDataToDomainMapper>().value,
            inject<PiecesDomainToDataMapper>().value,
        )
    } binds arrayOf(PiecesRepository::class)
    singleOf(::PiecesDomainToPresentationMapper)
    singleOf(::PiecesPresentationToDomainMapper)
    singleOf(::WhiteMoveUseCase)
    singleOf(::BlackMoveUseCase)
    singleOf(::FetchPiecesUseCase)
    singleOf(::ResetUseCase)
}
