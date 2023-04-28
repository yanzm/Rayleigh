package remote

import auth.TokenStore
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerAuthProvider
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
import io.ktor.client.plugins.plugin
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.http.isSuccess
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import model.ApiResult

interface BlueSkyApi {

    suspend fun createSession(
        username: String,
        appPassword: String
    ): ApiResult<CreateSessionResponse, ErrorResponse>

    suspend fun getTimeline(
        algorithm: String? = null,
        limit: Int? = null,
        cursor: String? = null
    ): ApiResult<TimelineResponse, ErrorResponse>
}

private const val ServiceProvider = "bsky.social"
private const val BaseUrl = "https://$ServiceProvider/xrpc/"

class KtorBlueSkyApi(
    private val tokenStore: TokenStore
) : BlueSkyApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                    classDiscriminator = "\$type"
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
        install(Auth) {
            bearer {
                loadTokens {
                    tokenStore.get()?.let {
                        BearerTokens(it.accessToken, it.refreshToken)
                    }
                }
            }
        }
    }

    private fun clearToken() {
        client.plugin(Auth).providers.filterIsInstance<BearerAuthProvider>()
            .firstOrNull()?.clearToken()
    }

    override suspend fun createSession(
        username: String,
        appPassword: String
    ): ApiResult<CreateSessionResponse, ErrorResponse> {
        try {
            val httpResponse = client.post("${BaseUrl}com.atproto.server.createSession") {
                contentType(ContentType.Application.Json)
                setBody(
                    CreateSessionBody(
                        identifier = username,
                        password = appPassword,
                    )
                )
            }

            return if (httpResponse.status.isSuccess()) {
                clearToken()
                ApiResult.Success(httpResponse.body())
            } else {
                ApiResult.Error(httpResponse.body())
            }
        } catch (e: Exception) {
            return ApiResult.Error(
                ErrorResponse(
                    "UnknownHostException",
                    "no internet"
                )
            )
        }
    }

    override suspend fun getTimeline(
        algorithm: String?,
        limit: Int?,
        cursor: String?
    ): ApiResult<TimelineResponse, ErrorResponse> {
        try {
            val httpResponse = client.get("${BaseUrl}app.bsky.feed.getTimeline") {
                contentType(ContentType.Application.Json)
                url {
                    algorithm?.let {
                        parameters.append("algorithm", algorithm)
                    }
                    limit?.let {
                        parameters.append("limit", limit.toString())
                    }
                    cursor?.let {
                        parameters.append("cursor", cursor)
                    }
                }
            }

            return if (httpResponse.status.isSuccess()) {
                ApiResult.Success(httpResponse.body())
            } else {
                ApiResult.Error(httpResponse.body())
            }
        } catch (e: Exception) {
            return ApiResult.Error(
                ErrorResponse(
                    "UnknownHostException",
                    "no internet"
                )
            )
        }
    }
}
