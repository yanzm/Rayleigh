package auth

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import model.ApiResult
import remote.BlueSkyApi
import remote.ErrorResponse

interface AuthRepository {

    suspend fun login(
        username: String,
        appPassword: String,
    ): ApiResult<Unit, ErrorResponse>

    val isLoggedIn: StateFlow<Boolean>
}

class DefaultAuthRepository(
    private val api: BlueSkyApi,
    private val settings: Settings,
) : AuthRepository {

    private val _isLoggedIn = MutableStateFlow(
        settings.getStringOrNull("accessJwt") != null
    )
    override val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    override suspend fun login(
        username: String,
        appPassword: String,
    ): ApiResult<Unit, ErrorResponse> {
        return when (val result = api.createSession(username, appPassword)) {
            is ApiResult.Success -> {
                settings.putString("accessJwt", result.data.accessJwt)
                settings.putString("refreshJwt", result.data.refreshJwt)
                _isLoggedIn.value = true
                ApiResult.Success(Unit)
            }

            is ApiResult.Error -> ApiResult.Error(result.e)
        }
    }
}
