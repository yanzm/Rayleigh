import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import di.AppComponent
import ui.home.HomeScreen
import ui.home.HomeViewModel
import ui.login.LoginScreen
import ui.login.LoginViewModel
import ui.theme.RayleighTheme
import viewmodel.AppViewModel

@Composable
fun App(
    appComponent: AppComponent,
    onOpenBrowser: (String) -> Unit = {}
) {
    RayleighTheme {
        val appViewModel = remember { AppViewModel(appComponent.authRepository) }
        val isLoggedIn = appViewModel.isLoggedIn.collectAsState().value
        if (isLoggedIn) {
            val viewModel = remember { HomeViewModel(appComponent.api) }
            HomeScreen(
                viewModel = viewModel
            )
        } else {
            val viewModel = remember { LoginViewModel(appComponent.authRepository) }
            LoginScreen(
                viewModel = viewModel,
                onOpenBrowser = onOpenBrowser,
            )
        }
    }
}
