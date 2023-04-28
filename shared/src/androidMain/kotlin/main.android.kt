import androidx.compose.runtime.Composable
import auth.AuthRepository

@Composable
fun MainView(
    authRepository: AuthRepository,
    onOpenBrowser: (String) -> Unit
) {
    App(
        authRepository = authRepository,
        onOpenBrowser = onOpenBrowser
    )
}
