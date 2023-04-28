package di

import auth.AuthRepository
import auth.DefaultAuthRepository
import auth.DefaultTokenStore
import auth.TokenStore
import org.koin.dsl.module
import remote.BlueSkyApi
import remote.KtorBlueSkyApi

val commonModule = module {
    single<TokenStore> { DefaultTokenStore(get()) }
    single<BlueSkyApi> { KtorBlueSkyApi(get()) }
    single<AuthRepository> { DefaultAuthRepository(get(), get()) }
}
