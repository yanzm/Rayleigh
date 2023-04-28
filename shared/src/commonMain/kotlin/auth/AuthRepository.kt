package auth

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
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
    private val tokenStore: TokenStore,
) : AuthRepository {

    private val _isLoggedIn = MutableStateFlow(
        tokenStore.get() != null
    )
    override val isLoggedIn: StateFlow<Boolean> get() = _isLoggedIn

    override suspend fun login(
        username: String,
        appPassword: String,
    ): ApiResult<Unit, ErrorResponse> {
        return withContext(Dispatchers.Default) {
            when (val result = api.createSession(username, appPassword)) {
                is ApiResult.Success -> {
                    tokenStore.save(result.data.accessJwt, result.data.refreshJwt)
                    _isLoggedIn.value = true
                    ApiResult.Success(Unit)
                }

                is ApiResult.Error -> ApiResult.Error(result.e)
            }
        }
    }
}
