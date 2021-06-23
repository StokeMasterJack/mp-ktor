pluginManagement {
    val kv = "1.5.0"
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id == "kotlin-jvm") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:$kv")
            }
            if (requested.id.id == "org.jetbrains.kotlin.kapt") {
                useModule("org.jetbrains.kotlin:kotlin-gradle-plugin:$kv")
            }
            if (requested.id.id == "kotlinx-serialization") {
                useModule("org.jetbrains.kotlin:kotlin-serialization:$kv")
            }
        }
    }

    repositories {
        mavenLocal()
        mavenCentral()
        maven("https://dl.bintray.com/kotlin/kotlin-dev")
        maven("https://dl.bintray.com/kotlin/kotlin-eap")
        maven("https://plugins.gradle.org/m2/")
    }
}


rootProject.name = "mp-ktor"