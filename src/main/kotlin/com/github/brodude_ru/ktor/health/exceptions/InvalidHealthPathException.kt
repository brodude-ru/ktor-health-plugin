package com.github.brodude_ru.ktor.health.exceptions

class InvalidHealthPathException(invalidPath: String) : Throwable(
    message = "Health-check request path '$invalidPath' is invalid"
)
