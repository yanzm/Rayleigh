import auth.AuthRepository
import di.appModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin

class AppComponent : KoinComponent {
    val authRepository: AuthRepository by inject()
}

fun initKoin() {
    startKoin {
        modules(appModule())
    }
}
