# Ktor Health plugin
This plugin allows you to enter a health check of your service on Ktor using the health-check path, when accessing which the status 200 OK will be returned using a GET request.

# Usage
Using the plugin is as simple as possible - as through the installation method, it is also possible (and recommended) to use the extension of the Ktor application class. When using the application class extension, you can create as many Health-Check paths as you need (for example, in each module), and the plugin itself will be installed in the application or provided automatically.

**Example 1: Using install method**
```kotlin
    fun Application.module() {
        install(Health) {
            path = "/health"
        }
    }
```

**Example 2: Using application extension function**
```kotlin
    fun Application.installHealth() = health {
        path = "/health"
    }
```

# Configuration
The plugin has only one setting - this is the GET request path. You can either use the setting in the code or the setting to the application configuration file. If you have not redefined the path of the health check request either in the code or in the HOCON configuration file ```application.conf```, the default path ```/health``` will be used.
In order to define the request path in ```application.conf```, you can add the following section:

```hocon
  ktor {
    health {
      path=/health
    }
  }
```