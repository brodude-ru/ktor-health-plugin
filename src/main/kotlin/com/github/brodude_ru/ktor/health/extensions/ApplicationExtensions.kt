package com.github.brodude_ru.ktor.health.extensions

import com.github.brodude_ru.ktor.health.config.HealthConfig
import com.github.brodude_ru.ktor.health.plugin.Health
import io.ktor.server.application.*

/**
 * Extension of the application class to install the Health plugin.
 * Allows you to configure the plugin for different modules by installing the plugin at the same time and using
 * configuration processing in modules.
 *
 * @param configure Lambda expression for configuring
 *
 * @see Application
 * @see HealthConfig
 * @see Health
 * @see pluginOrNull
 * @see Health.processConfig
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
fun Application.health(configure: HealthConfig.() -> Unit) {
    val plugin = pluginOrNull(Health)

    if (plugin != null) {
        val config = HealthConfig.create(environment.config).apply(configure)
        plugin.processConfig(config)
    } else install(Health, configure)
}
