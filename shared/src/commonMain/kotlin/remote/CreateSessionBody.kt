package remote

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionBody(
    val identifier: String,
    val password: String,
)
