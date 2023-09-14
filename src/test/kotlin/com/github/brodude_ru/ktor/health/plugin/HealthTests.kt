package com.github.brodude_ru.ktor.health.plugin

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import kotlin.test.Test
import kotlin.test.assertEquals

/**
 * A class containing tests for the Health plugin for Ktor.
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
class HealthTests {

    /**
     * Method of checking the correctness of the plugin's operation, operability and application of the route.
     *
     * @see testApplication
     * @see application
     * @see Health
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    @Test
    fun `Test Health route`() {
        val testPath = "/test/health"

        testApplication {
            application {
                install(Health) {
                    path = testPath
                }
            }

            runBlocking {
                val response = client.get(testPath)
                assertEquals(HttpStatusCode.OK, response.status)
            }
        }
    }

}
