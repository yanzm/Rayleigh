package di

import auth.AuthRepository
import auth.DefaultAuthRepository
import org.koin.dsl.module
import remote.BlueSkyApi
import remote.KtorBlueSkyApi

val commonModule = module {
    single<BlueSkyApi> { KtorBlueSkyApi() }
    single<AuthRepository> { DefaultAuthRepository(get(), get()) }
}
