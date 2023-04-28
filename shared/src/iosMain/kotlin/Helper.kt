import auth.AuthRepository
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.KeychainSettings
import com.russhwolf.settings.Settings
import di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.koin.dsl.module

class AppComponent : KoinComponent {
    val authRepository: AuthRepository by inject()
}

fun initKoin() {
    startKoin {
        modules(appModule() + iosModule)
    }
}

private val iosModule = module {
    single { createSettings() }
}

@OptIn(ExperimentalSettingsImplementation::class)
fun createSettings(): Settings = KeychainSettings(
    service = "MyEncryptedSettings"
)
