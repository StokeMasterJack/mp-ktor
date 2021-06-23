import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
    application
}

group = "fa.market"
version = "0.1.0-SNAPSHOT"

val kotlinVersion: String by project
val kotlinCoroutinesVersion: String by project
val kotlinxSerializationVersion: String by project
val kotlinxDateTimeVersion: String by project
val kotlinxHtmlVersion: String by project
val kotlinCssVersion: String by project
val ktorVersion: String by project
val logbackVersion: String by project
val slf4Version: String by project
val kotlinLoggingVersion: String by project

repositories {
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {


    implementation(kotlin("reflect", kotlinVersion))
    implementation(kotlin("stdlib-jdk8", kotlinVersion))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutinesVersion-native-mt")


    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDateTimeVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
    implementation("org.jetbrains:kotlin-css:$kotlinCssVersion")

    implementation("io.ktor:ktor-server-core:$ktorVersion")
    implementation("io.ktor:ktor-auth:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-locations:$ktorVersion")
    implementation("io.ktor:ktor-client-core:$ktorVersion")
    implementation("io.ktor:ktor-client-core-jvm:$ktorVersion")
    implementation("io.ktor:ktor-client-apache:$ktorVersion")
    implementation("io.ktor:ktor-auth-jwt:$ktorVersion")
    implementation("io.ktor:ktor-server-host-common:$ktorVersion")
    implementation("io.ktor:ktor-metrics:$ktorVersion")
    implementation("io.ktor:ktor-metrics-micrometer:$ktorVersion")
    implementation("io.ktor:ktor-html-builder:$ktorVersion")
    implementation("io.ktor:ktor-serialization:$ktorVersion")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")

    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("ch.qos.logback:logback-core:$logbackVersion")
    implementation("org.slf4j:slf4j-api:$slf4Version")
    implementation("io.github.microutils:kotlin-logging-jvm:$kotlinLoggingVersion")


    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
    testImplementation(kotlin("test", kotlinVersion))
}

tasks.withType<KotlinJvmCompile>().configureEach {
    kotlinOptions {
        apiVersion = "1.5"
        languageVersion = "1.5"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}


tasks.test {
    useJUnit()
}

application {
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=false")
    mainClass.set("fa.market.ApplicationKt")
}
