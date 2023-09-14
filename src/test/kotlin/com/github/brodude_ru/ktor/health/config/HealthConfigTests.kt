package com.github.brodude_ru.ktor.health.config

import com.github.brodude_ru.ktor.health.exceptions.InvalidHealthPathException
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * Tests for the Health Plugin Configuration for Ktor.
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
class HealthConfigTests {

    /**
     * This test checks the setting of the obviously correct value of the health check path.
     *
     * @see HealthConfig.path
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    @Test
    fun `Test for set correct health path`() {
        val testPath = "/test/health"
        val config = HealthConfig.create()

        config.apply {
            path = testPath
        }

        assertEquals(testPath, config.path)
    }

    /**
     * This test verifies that when an empty health check path is installed in the plugin configuration, an exception will be thrown.
     *
     * @see HealthConfig.path
     * @see InvalidHealthPathException
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    @Test
    fun `Test for empty health path`() {
        var failed = false
        val config = HealthConfig.create()

        try {
            config.apply {
                path = ""
            }
        } catch (throwable: Throwable) {
            failed = throwable is InvalidHealthPathException
        }

        assert(failed)
    }

    /**
     * This test checks that if you try to set an incorrect health check path in the Health plugin configuration, an exception will be thrown.
     *
     * @see HealthConfig.path
     * @see InvalidHealthPathException
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    @Test
    fun `Test for incorrect health path`() {
        var failed = false
        val config = HealthConfig.create()

        try {
            config.apply {
                path = ".test/health.failed"
            }
        } catch (throwable: Throwable) {
            failed = throwable is InvalidHealthPathException
        }

        assert(failed)
    }

}
