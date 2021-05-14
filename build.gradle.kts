@file:Suppress("PropertyName")

import org.gradle.plugins.ide.idea.model.IdeaLanguageLevel
import org.gradle.plugins.ide.idea.model.IdeaModule
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile

plugins {
    kotlin("jvm")
    id("de.otto.find.project-version")
    kotlin("plugin.serialization")
    application
    idea
    id("org.jetbrains.gradle.plugin.idea-ext")
}

val kotlinVersion: String by project
val kotlinCoroutinesVersion: String by project
val kotlinxSerializationVersion: String by project
val kotlinxDateTimeVersion: String by project
val kotlinxHtmlVersion: String by project
val kotlinCssVersion: String by project
val ktorVersion: String by project
val logbackVersion: String by project
val prometeusVersion: String by project
val jedisVersion: String by project

val gitCommitId = provider { file(".git/refs/heads/main").readText().trim() }

fun String.splitOnWhitespace(): List<String> = this.split("\\s".toRegex())
fun String.toFileSet(): Set<File> = this.splitOnWhitespace().map { File(it) }.toSet()

//fun IdeaModule.excludeDirs(s: String) {
//    excludeDirs = s.toFileSet()
//}

var IdeaModule.excludes: String
    get() = error("Not supported")
    set(s) {
        excludeDirs = s.toFileSet()
    }

group = "fa.market"

projectVersion {
    useSemanticVersioning()
}

repositories {
    mavenLocal()
    mavenCentral()
    maven { url = uri("https://maven.pkg.jetbrains.space/public/p/kotlinx-html/maven") }
    maven { url = uri("https://maven.pkg.jetbrains.space/kotlin/p/kotlin/kotlin-js-wrappers") }
}

dependencies {


    implementation(kotlin("reflect", kotlinVersion))

    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kotlinCoroutinesVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:$kotlinCoroutinesVersion-native-mt")


    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-hocon:$kotlinxSerializationVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-datetime:$kotlinxDateTimeVersion")
    implementation("org.jetbrains.kotlinx:kotlinx-html-jvm:$kotlinxHtmlVersion")
    implementation("org.jetbrains:kotlin-css:$kotlinCssVersion")

//    implementation("redis.clients:jedis:3.6.0")
    implementation("redis.clients:jedis:$jedisVersion")

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

    testImplementation("io.ktor:ktor-server-tests:$ktorVersion")
}



tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
    sourceCompatibility = "11"
    targetCompatibility = "11"
}

tasks.withType<KotlinJvmCompile>().configureEach {
    kotlinOptions {
        jvmTarget = "11"
//        suppressWarnings = true
        apiVersion = "1.5"
        languageVersion = "1.5"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
    }
}

application {
    mainClass.set("fa.mp.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=true")
}



idea {

    project {
        vcs = "Git"
        jdkName = "11"
        languageLevel = IdeaLanguageLevel(11)
        targetBytecodeVersion = JavaVersion.VERSION_11
    }

    module {
        this.excludes = ".gradle .idea build gradle docs resources/lib"
        this.isDownloadSources = true
        this.isDownloadJavadoc = false
    }
}



