package application.config

import com.natpryce.konfig.*

class EnvironmentConfig(
    config: Configuration = ConfigurationProperties.systemProperties() overriding
            EnvironmentVariables() overriding
            ConfigurationProperties.fromResource("defaults.properties")
) {
    val servicePort = config[SERVICE_PORT]
    val defaultContentType = config[DEFAULT_CONTENT_TYPE]

    companion object {
        val SERVICE_PORT by intType
        val DEFAULT_CONTENT_TYPE by stringType

    }

}