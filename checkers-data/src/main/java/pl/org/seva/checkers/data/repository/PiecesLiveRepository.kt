package pl.org.seva.checkers.data.repository

import pl.org.seva.checkers.data.datasource.PiecesDataSource
import pl.org.seva.checkers.data.mapper.PiecesDataToDomainMapper
import pl.org.seva.checkers.data.mapper.PiecesDomainToDataMapper
import pl.org.seva.checkers.data.model.PiecesDataModel
import pl.org.seva.checkers.domain.model.PiecesDomainModel
import pl.org.seva.checkers.domain.repository.PiecesRepository
import java.util.Collections

class PiecesLiveRepository(
    private val piecesDatasource: PiecesDataSource,
    private val piecesDataToDomainMapper: PiecesDataToDomainMapper,
    private val piecesDomainToDataMapper: PiecesDomainToDataMapper,
) : PiecesRepository {

    private val piecesStore = Collections.synchronizedMap(mutableMapOf<String, PiecesDataModel>())

    private val leaves = mutableSetOf<String>()

    override var root = ""
        private set

    init {
        reset()
    }

    override operator fun get(piecesId: String) =
        piecesDataToDomainMapper.toDomain(requireNotNull(piecesStore[piecesId]) { "wrong Id: $piecesId" })

    override operator fun set(piecesId: String, value: PiecesDomainModel) {
        piecesStore[piecesId] = piecesDomainToDataMapper.toData(value)
    }

    override fun getLeaves(level: Int): Iterable<String> {
        if (level < 0) return leaves
        return leaves.filter {
            get(it).level == level
        }
    }

    override fun find(sought: PiecesDomainModel): String {
        val filtered = piecesStore.filter {
            it.value.whiteMen == sought.whiteMen &&
                    it.value.blackMen == sought.blackMen &&
                    it.value.whiteKings == sought.whiteKings &&
                    it.value.blackKings == sought.blackKings
        }
        return if (filtered.isEmpty()) "" else filtered.keys.first()
    }

    override fun prune(id: String) {
        val toDelete = piecesStore.values.filter {
            var item = it
            while (true) {
                if (item.id == id) {
                    return@filter true
                }
                item = piecesStore[item.parent] ?: break
            }
            return@filter false
        }.map { it.id }
        toDelete.forEach {
            leaves.remove(it)
            piecesStore.remove(it)
        }
        piecesStore.forEach {
            it.value.level--
            if (it.value.level == 0) {
                root = it.value.id
            }
        }
    }

    override fun updateState(state: PiecesDomainModel) {
        root = state.id
        leaves.clear()
        piecesStore.clear()
        piecesStore[root] = piecesDomainToDataMapper.toData(state)
        leaves.add(root)
    }

    override fun addLeaf(state: PiecesDomainModel) {
        piecesStore[state.id] = piecesDomainToDataMapper.toData(state)
        leaves.remove(state.parent)
        leaves.add(state.id)
    }

    override fun reset() {
        piecesDatasource.load().forEach {
            piecesStore[it.id] = it
        }
        leaves.addAll(piecesStore.values.map { it.id })
        piecesStore.forEach {
            if (it.value.parent.isEmpty()) {
                root = it.key
            }
            else {
                leaves.remove(it.value.parent)
                var item = it.value
                while (item.parent.isNotEmpty()) {
                    item = requireNotNull(piecesStore[item.parent]) { "Invalid parent" }
                    it.value.level++
                }
            }
        }
    }

    override fun getImmediateMoves(): Iterable<PiecesDomainModel> {
        return piecesStore.values.filter { it.level == 1 }.map { piecesDataToDomainMapper.toDomain(it) }
    }

}
