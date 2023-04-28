import androidx.compose.ui.window.ComposeUIViewController
import di.AppComponent

fun MainViewController(
    appComponent: AppComponent,
    onOpenBrowser: (String) -> Unit
) = ComposeUIViewController {
    App(
        appComponent = appComponent,
        onOpenBrowser = onOpenBrowser
    )
}
