package pl.org.seva.checkers.datasource

import pl.org.seva.checkers.data.datasource.PiecesDataSource
import pl.org.seva.checkers.data.model.PiecesDataModel
import pl.org.seva.checkers.datasource.mapper.PiecesMemoryToDataMapper
import pl.org.seva.checkers.datasource.model.IterableOfPieces

class PiecesLiveDatasource(
    private val piecesStore: IterableOfPieces,
    private val piecesMemoryToDataMapper: PiecesMemoryToDataMapper,
) : PiecesDataSource {

    override fun load(): Iterable<PiecesDataModel> {
        return piecesStore.iterable.map { piecesMemoryToDataMapper.toData(it) }
    }

}
