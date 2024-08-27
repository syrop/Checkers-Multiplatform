package pl.org.seva.multiplatform.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.binds
import org.koin.dsl.module
import pl.org.seva.checkers.data.datasource.PiecesDataSource
import pl.org.seva.checkers.datasource.PiecesLiveDatasource
import pl.org.seva.checkers.datasource.mapper.PiecesMemoryToDataMapper
import pl.org.seva.checkers.datasource.model.PiecesMemoryModel
import pl.org.seva.checkers.datasource.model.IterableOfPieces
import java.util.UUID

val WHITE_START_POSITION = listOf(0 to 7, 1 to 6, 2 to 7, 3 to 6, 4 to 7, 5 to 6, 6 to 7, 7 to 6, 0 to 5, 2 to 5, 4 to 5, 6 to 5)
val BLACK_START_POSITION = listOf(0 to 1, 1 to 0, 2 to 1, 3 to 0, 4 to 1, 5 to 0, 6 to 1, 7 to 0, 1 to 2, 3 to 2, 5 to 2, 7 to 2)

val dataSourceModule = module {
    singleOf(::PiecesMemoryToDataMapper)
    single {
        IterableOfPieces(setOf(
            PiecesMemoryModel(
                UUID.randomUUID().toString(),
                "",
                WHITE_START_POSITION,
                BLACK_START_POSITION,
                emptyList(),
                emptyList(),
            )
        ))
    } binds arrayOf(IterableOfPieces::class)
    single {
        PiecesLiveDatasource(
            inject<IterableOfPieces>().value,
            inject<PiecesMemoryToDataMapper>().value,
        )
    } binds arrayOf(PiecesDataSource::class)

}
