package net.yanzm.rayleigh

import android.app.Application
import auth.AuthRepository
import di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext.startKoin

class RayleighApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@RayleighApplication)
            androidLogger()
            modules(appModule())
        }
    }
}
