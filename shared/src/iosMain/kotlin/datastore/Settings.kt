package datastore

import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import di.ServiceContainer

actual fun initServiceContainer() {
    ServiceContainer.register(
        "settings",
        createSettings()
    )
}

@OptIn(ExperimentalSettingsImplementation::class)
fun createSettings(): Settings = KeychainSettings(
    service = "MyEncryptedSettings"
)
