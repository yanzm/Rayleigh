package net.yanzm.rayleigh

import android.app.Application
import datastore.initServiceContainer
import di.ServiceContainer

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        ServiceContainer.register(
            "context",
            this
        )

        initServiceContainer()
    }
}
