import androidx.compose.runtime.Composable
import di.AppComponent

@Composable
fun MainView(appComponent: AppComponent) {
    App(
        appComponent = appComponent,
        onOpenBrowser = {
            // TODO
        }
    )
}
