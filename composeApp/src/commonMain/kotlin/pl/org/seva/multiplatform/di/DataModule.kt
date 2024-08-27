package pl.org.seva.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import pl.org.seva.checkers.data.mapper.PiecesDataToDomainMapper
import pl.org.seva.checkers.data.mapper.PiecesDomainToDataMapper

val dataModule = module {
    singleOf(::PiecesDataToDomainMapper)
    singleOf(::PiecesDomainToDataMapper)
}
