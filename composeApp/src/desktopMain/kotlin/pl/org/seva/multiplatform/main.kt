package pl.org.seva.multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import org.koin.core.logger.Level
import org.koin.java.KoinJavaComponent.inject
import org.koin.mp.KoinPlatform.startKoin
import pl.org.seva.checkers.presentation.GamePresentation
import pl.org.seva.multiplatform.di.dataModule
import pl.org.seva.multiplatform.di.dataSourceModule
import pl.org.seva.multiplatform.di.presentationModule
import pl.org.seva.multiplatform.di.uiModule
import pl.org.seva.multiplatform.ui.mapper.PiecesPresentationToUiMapper

fun main() = application {

    startKoin(
        modules = listOf(dataModule, dataSourceModule, presentationModule, uiModule),
        level = Level.DEBUG,
    )

    val presentation: GamePresentation by inject(GamePresentation::class.java)
    val piecesPresentationToUiMapper: PiecesPresentationToUiMapper by inject(
        PiecesPresentationToUiMapper::class.java)

    Window(
        onCloseRequest = ::exitApplication,
        title = "Checkers Multiplatform",
    ) {
        App(presentation, piecesPresentationToUiMapper)
        presentation.reset(CoroutineScope(Dispatchers.Main.immediate))
    }

}
