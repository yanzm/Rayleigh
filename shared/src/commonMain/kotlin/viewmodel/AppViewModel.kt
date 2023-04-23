package viewmodel

import auth.AuthRepository
import auth.DefaultAuthRepository
import di.ServiceContainer
import kotlinx.coroutines.flow.StateFlow

class AppViewModel : CommonViewModel() {

    private val authRepository: AuthRepository = DefaultAuthRepository(
        api = ServiceContainer.get("api"),
        settings = ServiceContainer.get("settings")
    )

    val isLoggedIn: StateFlow<Boolean> = authRepository.isLoggedIn
}
