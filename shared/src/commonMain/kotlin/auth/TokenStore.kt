package auth

import com.russhwolf.settings.Settings

data class Tokens(val accessToken: String, val refreshToken: String)

interface TokenStore {

    fun get(): Tokens?

    fun save(accessJwt: String, refreshJwt: String)
}

class DefaultTokenStore(
    private val settings: Settings,
) : TokenStore {

    override fun get(): Tokens? {
        val a = settings.getStringOrNull("accessJwt")
        val r = settings.getStringOrNull("refreshJwt")
        return if (a != null && r != null) {
            Tokens(a, r)
        } else {
            null
        }
    }

    override fun save(accessJwt: String, refreshJwt: String) {
        settings.putString("accessJwt", accessJwt)
        settings.putString("refreshJwt", refreshJwt)
    }
}
