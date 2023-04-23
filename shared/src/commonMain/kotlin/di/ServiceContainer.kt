package di

object ServiceContainer {

    private val services = mutableMapOf<String, Any>()

    fun register(key: String, service: Any) {
        services[key] = service
    }

    fun <T> get(key: String): T {
        return services[key] as T
    }
}
