package pl.org.seva.multiplatform

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import org.koin.java.KoinJavaComponent.inject
import pl.org.seva.checkers.presentation.GamePresentation

class GameViewModel : ViewModel() {

    private val presentation: GamePresentation by inject(GamePresentation::class.java)

        fun reset() = presentation.reset(viewModelScope)
}
