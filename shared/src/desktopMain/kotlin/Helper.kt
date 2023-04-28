
import di.appModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

fun initKoin() {
    startKoin {
        modules(appModule() + desktopModule)
    }
}

private val desktopModule = module {
    // TODO
//    single { createSettings() }
}

// fun createSettings(): Settings = Settings()
