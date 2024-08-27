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
    MaterialTheme {
        Board()
        Pieces(
            piecesPresentationToUiMapper.toUi(presentation.viewState.collectAsState().value.pieces),
            onStoreState = {},
            onValidMove = { _, _, _, _, _, _, _ ->
            },
            onInvalidMove = {}
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
                    text = "White Won",
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
                    text = "Black Won",
                    fontSize = 34.sp,
                )
            }
        }
    }
//        var showContent by remember { mutableStateOf(false) }
//        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//            Button(onClick = { showContent = !showContent }) {
//                Text("Click me!")
//            }
//            AnimatedVisibility(showContent) {
//                val greeting = remember { Greeting().greet() }
//                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
//                    Image(painterResource(Res.drawable.compose_multiplatform), null)
//                    Text("Compose: $greeting")
//                }
//            }
//        }

}