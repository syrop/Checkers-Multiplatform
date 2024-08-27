package pl.org.seva.multiplatform

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import checkersmultiplatform.composeapp.generated.resources.Res
import checkersmultiplatform.composeapp.generated.resources.black_won
import checkersmultiplatform.composeapp.generated.resources.white_won
import org.jetbrains.compose.resources.stringResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import pl.org.seva.checkers.presentation.GamePresentation
import pl.org.seva.multiplatform.ui.mapper.PiecesPresentationToUiMapper

import pl.org.seva.multiplatform.ui.view.Board
import pl.org.seva.multiplatform.ui.view.Pieces

@Composable
@Preview
fun App(
    presentation: GamePresentation,
    piecesPresentationToUiMapper: PiecesPresentationToUiMapper
) {
    val coroutineScope = rememberCoroutineScope()

    MaterialTheme {
        Board()
        Pieces(
            piecesPresentationToUiMapper.toUi(presentation.viewState.collectAsState().value.pieces),
            onStoreState = {
                presentation.storeState()
            },
            onValidMove = { x1, y1, beatingX, beatingY, x2, y2, isKing ->
                presentation.removeWhite(x1, y1)
                presentation.removeBlack(beatingX to beatingY)
                presentation.addWhite(x2, y2, isKing, coroutineScope)
                if (presentation.viewState.value.pieces.blackMen.toSet().isEmpty() &&
                    presentation.viewState.value.pieces.blackKings.toSet().isEmpty()) {
                    presentation.setWhiteWon()
                }
            },
            onInvalidMove = {
                presentation.restoreState()
            }
        )
        if (presentation.viewState.collectAsState().value.isLoading) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                CircularProgressIndicator()
            }
        }
        if (presentation.whiteWon.collectAsState().value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(Res.string.white_won),
                    fontSize = 34.sp,
                )
            }
        }
        if (presentation.blackWon.collectAsState().value) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.fillMaxSize()
            ) {
                Text(
                    text = stringResource(Res.string.black_won),
                    fontSize = 34.sp,
                )
            }
        }
    }

}
