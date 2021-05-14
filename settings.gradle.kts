pluginManagement {
    plugins {
        val kotlinVersion: String by settings
        val ideaExtPluginVersion: String by settings

        kotlin("jvm") version kotlinVersion

        id("de.otto.find.project-version") version "1.1.0"
        id("org.jetbrains.gradle.plugin.idea-ext") version ideaExtPluginVersion
    }
}

rootProject.name = "mp-ktor"