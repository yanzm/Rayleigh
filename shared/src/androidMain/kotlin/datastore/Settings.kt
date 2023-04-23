package datastore

import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import di.ServiceContainer
import remote.KtorBlueSkyApi

actual fun initServiceContainer() {
    val app = ServiceContainer.get<Context>("context")

    ServiceContainer.register(
        "settings",
        createSettings(app)
    )

    ServiceContainer.register(
        "api",
        KtorBlueSkyApi()
    )
}

fun createSettings(context: Context): Settings = SharedPreferencesSettings(
    EncryptedSharedPreferences.create(
        context,
        "MyEncryptedSettings",
        MasterKey.Builder(context)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    ),
    false
)
