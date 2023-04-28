import androidx.compose.runtime.Composable

actual fun getPlatformName(): String = "Android"

@Composable
fun MainView(
    onOpenBrowser: (String) -> Unit
) {
    App(
        onOpenBrowser = onOpenBrowser
    )
}
