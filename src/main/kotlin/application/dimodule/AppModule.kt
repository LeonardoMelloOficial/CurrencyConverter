package application.dimodule

import LoggerDelegate
import application.config.EnvironmentConfig
import org.koin.dsl.module

object AppModule {

    fun modules() = module {
        single { EnvironmentConfig() }
        single { LoggerDelegate().logger }
    }
}