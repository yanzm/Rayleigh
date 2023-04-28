package di

import auth.AuthRepository
import auth.DefaultAuthRepository
import org.koin.dsl.module

val commonModule = module {
    single<AuthRepository> { DefaultAuthRepository() }
}
