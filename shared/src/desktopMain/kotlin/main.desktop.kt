import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Desktop"

@Composable
fun MainView() {
    App(
        onOpenBrowser = {
            // TODO
        }
    )
}

@Preview
@Composable
fun AppPreview() {
    App()
}
