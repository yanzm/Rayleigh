import androidx.compose.ui.window.ComposeUIViewController

actual fun getPlatformName(): String = "iOS"

fun MainViewController(
    onOpenBrowser: (String) -> Unit
) = ComposeUIViewController {
    App(
        onOpenBrowser = onOpenBrowser
    )
}
