package di

import auth.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AppComponent : KoinComponent {
    val authRepository: AuthRepository by inject()
}
