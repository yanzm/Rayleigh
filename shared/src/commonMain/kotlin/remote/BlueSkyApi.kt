package remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.plugins.logging.SIMPLE
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
}

private const val ServiceProvider = "bsky.social"
private const val BaseUrl = "https://$ServiceProvider/xrpc/"

class KtorBlueSkyApi : BlueSkyApi {

    private val client = HttpClient {
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                }
            )
        }
        install(Logging) {
            logger = Logger.SIMPLE
            level = LogLevel.ALL
        }
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
