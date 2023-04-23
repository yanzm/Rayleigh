import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import ui.home.HomeScreen
import ui.login.LoginScreen
import ui.login.LoginViewModel
import ui.theme.RayleighTheme
import viewmodel.AppViewModel

@Composable
fun App(
    onOpenBrowser: (String) -> Unit = {}
) {
    RayleighTheme {
        val appViewModel = remember { AppViewModel() }
        val isLoggedIn = appViewModel.isLoggedIn.collectAsState().value
        if (isLoggedIn) {
            HomeScreen()
        } else {
            val viewModel = remember { LoginViewModel() }
            LoginScreen(
                viewModel = viewModel,
                onOpenBrowser = onOpenBrowser,
            )
        }
    }
}

expect fun getPlatformName(): String
