
import application.config.EnvironmentConfig
import application.dimodule.AppModule
import io.javalin.Javalin
import org.koin.core.KoinComponent
import org.koin.core.context.startKoin
import org.koin.core.inject

class KotlinStudyApp : KoinComponent {

    private val environmentConfig : EnvironmentConfig by inject()
    private val logger by logster

    fun init(): Javalin {
        logger.info("Service initialized")
        val app = Javalin.create { config ->
            config.defaultContentType = "application/json"
//        application.config.addStaticFiles("/public")
            config.enableCorsForAllOrigins()
        }
            .start(environmentConfig.servicePort)
        app.get("/") { ctx -> ctx.result("Hello World") }
        return app
    }

    companion object {
        init {
            startKoin {
                environmentProperties()
                modules(AppModule.modules())
            }
        }
    }


}

fun main(){
    KotlinStudyApp().init();
}