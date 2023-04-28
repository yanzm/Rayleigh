package di

import auth.AuthRepository
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import remote.BlueSkyApi

class AppComponent : KoinComponent {
    val authRepository: AuthRepository by inject()
    val api: BlueSkyApi by inject()
}
