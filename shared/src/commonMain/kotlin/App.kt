import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import ui.login.LoginScreen
import ui.login.LoginViewModel
import ui.theme.RayleighTheme

@Composable
fun App(
    onOpenBrowser: (String) -> Unit = {}
) {
    RayleighTheme {
        val viewModel = remember { LoginViewModel() }
        LoginScreen(
            viewModel = viewModel,
            onOpenBrowser = onOpenBrowser,
        )
    }
}

expect fun getPlatformName(): String
