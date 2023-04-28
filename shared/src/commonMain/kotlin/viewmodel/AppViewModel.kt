package viewmodel

import auth.AuthRepository
import kotlinx.coroutines.flow.StateFlow

class AppViewModel(authRepository: AuthRepository) : CommonViewModel() {

    val isLoggedIn: StateFlow<Boolean> = authRepository.isLoggedIn
}
