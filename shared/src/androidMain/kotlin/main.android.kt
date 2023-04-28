import androidx.compose.runtime.Composable
import di.AppComponent

@Composable
fun MainView(
    appComponent: AppComponent,
    onOpenBrowser: (String) -> Unit
) {
    App(
        appComponent = appComponent,
        onOpenBrowser = onOpenBrowser
    )
}
