package pl.org.seva.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.org.seva.checkers.domain.cleanarchitecture.usecase.UseCaseExecutor
import pl.org.seva.checkers.domain.usecase.BlackMoveUseCase
import pl.org.seva.checkers.domain.usecase.FetchPiecesUseCase
import pl.org.seva.checkers.domain.usecase.ResetUseCase
import pl.org.seva.checkers.domain.usecase.WhiteMoveUseCase
import pl.org.seva.checkers.presentation.GamePresentation
import pl.org.seva.checkers.presentation.mapper.PiecesDomainToPresentationMapper
import pl.org.seva.checkers.presentation.mapper.PiecesPresentationToDomainMapper
import pl.org.seva.multiplatform.ui.mapper.PiecesPresentationToUiMapper

val uiModule = module {
    single {
        GamePresentation(
            inject<PiecesDomainToPresentationMapper>().value,
            inject<PiecesPresentationToDomainMapper>().value,
            inject<WhiteMoveUseCase>().value,
            inject<BlackMoveUseCase>().value,
            inject<FetchPiecesUseCase>().value,
            inject<ResetUseCase>().value
        ) { UseCaseExecutor() }
    }
    singleOf(::PiecesPresentationToUiMapper)
}
