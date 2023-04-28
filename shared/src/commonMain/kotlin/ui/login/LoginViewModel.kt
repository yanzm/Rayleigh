package ui.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import auth.AuthRepository
import kotlinx.coroutines.launch
import model.ApiResult
import remote.ErrorResponse
import viewmodel.CommonViewModel

class LoginViewModel(
    private val authRepository: AuthRepository
) : CommonViewModel() {

    var uiState by mutableStateOf<UiState>(UiState.Idle)
        private set

    fun login(
        username: String,
        appPassword: String,
    ) {
        if (uiState is UiState.Submitting) {
            return
        }

        val regex = "[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}-[a-zA-Z0-9]{4}".toRegex()
        if (!regex.matches(appPassword)) {
            uiState = UiState.Error(LoginError.InvalidAppPassword)
            return
        }

        uiState = UiState.Submitting

        viewModelScope.launch {
            val result = authRepository.login(username, appPassword)
            uiState = when (result) {
                is ApiResult.Success -> UiState.Success
                is ApiResult.Error -> UiState.Error(LoginError.ApiError(result.e))
            }
        }
    }

    fun consumeError() {
        uiState = UiState.Idle
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
