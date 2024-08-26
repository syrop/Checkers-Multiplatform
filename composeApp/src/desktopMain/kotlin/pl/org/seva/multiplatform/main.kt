package pl.org.seva.multiplatform

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Checkers Multiplatform",
    ) {
        App()
    }
}