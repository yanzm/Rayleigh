import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import auth.AuthRepository
import ui.login.LoginScreen
import ui.login.LoginViewModel
import ui.theme.RayleighTheme

@Composable
fun App(
    authRepository: AuthRepository,
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
