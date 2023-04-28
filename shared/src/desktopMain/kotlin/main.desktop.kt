import androidx.compose.runtime.Composable
import auth.AuthRepository

@Composable
fun MainView(authRepository: AuthRepository) {
    App(
        authRepository = authRepository,
        onOpenBrowser = {
            // TODO
        }
    )
}
