package com.github.brodude_ru.ktor.health.plugin

import com.github.brodude_ru.ktor.health.config.HealthConfig
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.util.*

/**
 * The Health plugin class for Ktor.
 *
 * @param application Ktor application instance
 * @param config Initial Health plugin configuration
 *
 * @see Application
 * @see HealthConfig
 * @see processConfig
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
class Health private constructor(private val application: Application, config: HealthConfig) {

    /**
     * Plugin initialization block. Just passes the configuration to the processing method.
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    init {
        processConfig(config)
    }


    /**
     * The method of processing the plugin configuration. Creates a health GET request route.
     *
     * @param config Plugin configuration for processing
     *
     * @see HealthConfig
     *
     * @author Sergey Grigorov (https://gitlab.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    internal fun processConfig(config: HealthConfig) {
        application.routing {
            get(config.path) {
                call.respond(HttpStatusCode.OK)
            }
        }
    }


    /**
     * A companion object of the plugin class that implements the interface of the application's base plugin.
     *
     * @see Application
     * @see HealthConfig
     * @see Health
     * @see BaseApplicationPlugin
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    companion object : BaseApplicationPlugin<Application, HealthConfig, Health> {

        /**
         * The attribute key of the plugin, which also contains the plugin name.
         *
         * @see AttributeKey
         * @see BaseApplicationPlugin.key
         *
         * @author Sergey Grigorov (https://github.com/brodude-ru)
         * @since 0.0.1
         */
        override val key = AttributeKey<Health>("ktor-health-plugin")


        /**
         * Overloading the plugin installation method for the Ktor module.
         *
         * @param pipeline Plugin applying pipeline
         * @param configure Plugin configuring lambda
         *
         * @return Plugin instance
         *
         * @see BaseApplicationPlugin.install
         *
         * @author Sergey Grigorov (https://github.com/brodude-ru)
         * @since 0.0.1 - Added
         */
        override fun install(pipeline: Application, configure: HealthConfig.() -> Unit): Health {
            val config = HealthConfig.create(pipeline.environment.config).apply(configure)
            return Health(pipeline, config)
        }

    }

}
