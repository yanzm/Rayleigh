package ui.login

import auth.DefaultAuthRepository
import di.ServiceContainer
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import model.ApiResult
import remote.ErrorResponse
import viewmodel.CommonViewModel

class LoginViewModel : CommonViewModel() {

    private val authRepository = DefaultAuthRepository(
        api = ServiceContainer.get("api"),
        settings = ServiceContainer.get("settings")
    )

    private val _uiState = MutableStateFlow<UiState>(UiState.Idle)
    val uiState: StateFlow<UiState> get() = _uiState

    fun login(
        username: String,
        appPassword: String,
    ) {
        if (_uiState.value is UiState.Submitting) {
            return
        }

        val regex = "[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}".toRegex()
        if (!regex.matches(appPassword)) {
            _uiState.value = UiState.Error(LoginError.InvalidAppPassword)
            return
        }

        _uiState.value = UiState.Submitting

        viewModelScope.launch {
            val result = withContext(Dispatchers.Default) {
                authRepository.login(username, appPassword)
            }
            _uiState.value = when (result) {
                is ApiResult.Success -> UiState.Success
                is ApiResult.Error -> UiState.Error(LoginError.ApiError(result.e))
            }
        }
    }

    fun consumeError() {
        _uiState.value = UiState.Idle
    }

    sealed interface UiState {
        object Idle : UiState
        object Submitting : UiState
        object Success : UiState
        data class Error(val e: LoginError) : UiState
    }

    sealed interface LoginError {
        object InvalidAppPassword : LoginError
        data class ApiError(val e: ErrorResponse) : LoginError
    }
}
