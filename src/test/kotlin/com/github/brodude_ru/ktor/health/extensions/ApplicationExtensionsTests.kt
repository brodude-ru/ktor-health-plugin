package com.github.brodude_ru.ktor.health.extensions

import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.server.testing.*
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * A class containing tests for extending the application class for the Health plugin.
 *
 * @author Sergey Grigorov (https://github.com/brodude-ru)
 * @since 0.0.1 - Added
 */
class ApplicationExtensionsTests {

    /**
     * Checks the correctness of the health GET request route.
     *
     * @see testApplication
     * @see health
     *
     * @author Sergey Grigorov (https://github.com/brodude-ru)
     * @since 0.0.1 - Added
     */
    @Test
    fun `Test application extension for Health plugin`() {
        val testPath = "/test/path"

        testApplication {
            application {
                health {
                    path = testPath
                }
            }

            val response = client.get(testPath)
            assertEquals(HttpStatusCode.OK, response.status)
        }
    }

}
