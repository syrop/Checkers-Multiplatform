package pl.org.seva.multiplatform

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import org.koin.java.KoinJavaComponent.inject
import pl.org.seva.checkers.presentation.GamePresentation
import pl.org.seva.multiplatform.ui.mapper.PiecesPresentationToUiMapper

class MainActivity : ComponentActivity() {

    private val presentation: GamePresentation by inject(GamePresentation::class.java)
    private val piecesPresentationToUiMapper: PiecesPresentationToUiMapper by inject(PiecesPresentationToUiMapper::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App(presentation, piecesPresentationToUiMapper)
        }
    }

}