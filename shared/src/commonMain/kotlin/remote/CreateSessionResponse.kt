package remote

import kotlinx.serialization.Serializable

@Serializable
data class CreateSessionResponse(
    val accessJwt: String,
    val refreshJwt: String,
    val handle: String,
    val did: String,
)
