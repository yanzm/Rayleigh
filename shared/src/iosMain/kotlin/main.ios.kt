import androidx.compose.ui.window.ComposeUIViewController
import auth.AuthRepository

fun MainViewController(
    authRepository: AuthRepository,
    onOpenBrowser: (String) -> Unit
) = ComposeUIViewController {
    App(
        authRepository = authRepository,
        onOpenBrowser = onOpenBrowser
    )
}
