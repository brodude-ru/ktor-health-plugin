package com.github.brodude_ru.ktor.health.config

import com.github.brodude_ru.ktor.health.exceptions.InvalidHealthPathException
import io.ktor.server.config.*

/**
 * Health Plugin configuration class for Ktor.
 *
 * @param applicationConfig Application config instance, null by default
 *
 * @see ApplicationConfig
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
class HealthConfig private constructor(applicationConfig: ApplicationConfig? = null) {


    /**
     * Path for health-check GET request.
     * During the setting of the value, the path is validated through a Regex expression.
     * If the path is not valid, an exception will be thrown.
     *
     * @throws InvalidHealthPathException
     *
     * @see InvalidHealthPathException
     *
     * @see getPathValid
     *
     * @author Sergey Grigorov (https://gtihub.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    var path = "/health"
        set (value) {
            val valid = getPathValid(value)

            if (!valid) {
                throw InvalidHealthPathException(value)
            }

            field = value
        }


    /**
     * Process application config for default values initialization.
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    init {
        applicationConfig?.propertyOrNull("ktor.health.path")?.getString()?.let { path = it }
    }


    /**
     * Checks the passed path using a regular expression and returns true if the path is valid and false if not.
     *
     * @param path Validating path
     *
     * @return Validation result (boolean value - valid or not)
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    private fun getPathValid(path: String): Boolean {
        val regex = Regex("^/(?:\\w+/)*\\w+\$")
        return path.matches(regex)
    }


    /**
     * Companion object of the configuration class.
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    companion object {

        /**
         * Creates a configuration object and passes a reference to the application config object to the constructor.
         * This method is static and final for the Java.
         *
         * @param applicationConfig Application config instance, null by default
         *
         * @see JvmStatic
         * @see ApplicationConfig
         * @see HealthConfig
         *
         * @return Health Plugin Configuration instance
         *
         * @author Sergey Grigorov (https://github.com/brodude-ru)
         * @since 0.0.1 - Added
         */
        @JvmStatic
        internal fun create(applicationConfig: ApplicationConfig? = null) = HealthConfig(applicationConfig)

    }

}
