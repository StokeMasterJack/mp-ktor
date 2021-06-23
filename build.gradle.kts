@file:Suppress("PropertyName")

import org.gradle.plugins.ide.idea.model.IdeaModule
import org.gradle.plugins.ide.idea.model.IdeaProject
import org.jetbrains.gradle.ext.ActionDelegationConfig
import org.jetbrains.gradle.ext.CodeStyleConfig
import org.jetbrains.gradle.ext.CopyrightConfiguration
import org.jetbrains.gradle.ext.EncodingConfiguration
import org.jetbrains.gradle.ext.GroovyCompilerConfiguration
import org.jetbrains.gradle.ext.IdeaCompilerConfiguration
import org.jetbrains.gradle.ext.Inspection
import org.jetbrains.gradle.ext.ModuleSettings
import org.jetbrains.gradle.ext.ModuleTypesConfig
import org.jetbrains.gradle.ext.PackagePrefixContainer
import org.jetbrains.gradle.ext.ProjectSettings
import org.jetbrains.gradle.ext.RunConfigurationContainer
import org.jetbrains.gradle.ext.TaskTriggersConfig
import org.jetbrains.gradle.ext.TopLevelArtifact
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmCompile
import java.io.File

plugins {
    kotlin("jvm") version "1.5.0"
    kotlin("plugin.serialization") version "1.5.0"
    id("org.jetbrains.gradle.plugin.idea-ext") version "1.0"
    application
    idea
}

group = "fa.market"
version = "0.1.0-SNAPSHOT"

enum class Mode {
    Test, Dev, Stage, Prod
}


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
val prometeusVersion: String by project
val jedisVersion: String by project

val gitCommitId = provider { file(".git/refs/heads/main").readText().trim() }

fun String.splitOnWhitespace(): List<String> = this.split("\\s".toRegex())
fun String.toFileSet(): Set<File> = this.splitOnWhitespace().map { File(it) }.toSet()

var IdeaModule.excludes: String
    get() = error("Not supported")
    set(s) {
        excludeDirs = s.toFileSet()
    }


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

//    implementation("redis.clients:jedis:3.6.0")
//    implementation("redis.clients:jedis:$jedisVersion")

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



tasks.withType<JavaCompile>().configureEach {
    options.encoding = "UTF-8"
//    sourceCompatibility = "1.8"
//    targetCompatibility = "1.8"
}



tasks.withType<KotlinJvmCompile>().configureEach {
    kotlinOptions {
        apiVersion = "1.5"
        languageVersion = "1.5"
        freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
//        jvmTarget="1.8"
    }
}


tasks.withType<GradleBuild>().configureEach { 
    
    doLast {
        val rtcp = configurations.runtimeClasspath.resolve()
        logger.warn(rtcp.toString())

    }
}


@Suppress("UNCHECKED_CAST")
val IdeaProject.settings: ProjectSettings
    get() = (this as ExtensionAware).extensions.getByName("settings") as ProjectSettings

fun IdeaProject.settings(configure: Action<ProjectSettings>): Unit =
    (this as ExtensionAware).extensions.configure("settings", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.delegateActions: ActionDelegationConfig
    get() = (this as ExtensionAware).extensions.getByName("delegateActions") as ActionDelegationConfig

fun ProjectSettings.delegateActions(configure: Action<ActionDelegationConfig>): Unit =
    (this as ExtensionAware).extensions.configure("delegateActions", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.taskTriggers: TaskTriggersConfig
    get() = (this as ExtensionAware).extensions.getByName("taskTriggers") as TaskTriggersConfig

fun ProjectSettings.taskTriggers(configure: Action<TaskTriggersConfig>): Unit =
    (this as ExtensionAware).extensions.configure("taskTriggers", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.compiler: IdeaCompilerConfiguration
    get() = (this as ExtensionAware).extensions.getByName("compiler") as IdeaCompilerConfiguration

fun ProjectSettings.compiler(configure: Action<IdeaCompilerConfiguration>): Unit =
    (this as ExtensionAware).extensions.configure("compiler", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.groovyCompiler: GroovyCompilerConfiguration
    get() = (this as ExtensionAware).extensions.getByName("groovyCompiler") as GroovyCompilerConfiguration

fun ProjectSettings.groovyCompiler(configure: Action<GroovyCompilerConfiguration>): Unit =
    (this as ExtensionAware).extensions.configure("groovyCompiler", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.codeStyle: CodeStyleConfig
    get() = (this as ExtensionAware).extensions.getByName("codeStyle") as CodeStyleConfig

fun ProjectSettings.codeStyle(configure: Action<CodeStyleConfig>): Unit =
    (this as ExtensionAware).extensions.configure("codeStyle", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.copyright: CopyrightConfiguration
    get() = (this as ExtensionAware).extensions.getByName("copyright") as CopyrightConfiguration

fun ProjectSettings.copyright(configure: Action<CopyrightConfiguration>): Unit =
    (this as ExtensionAware).extensions.configure("copyright", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.encodings: EncodingConfiguration
    get() = (this as ExtensionAware).extensions.getByName("encodings") as EncodingConfiguration

fun ProjectSettings.encodings(configure: Action<EncodingConfiguration>): Unit =
    (this as ExtensionAware).extensions.configure("encodings", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.runConfigurations: RunConfigurationContainer
    get() = (this as ExtensionAware).extensions.getByName("runConfigurations") as RunConfigurationContainer

fun ProjectSettings.runConfigurations(configure: Action<RunConfigurationContainer>): Unit =
    (this as ExtensionAware).extensions.configure("runConfigurations", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.inspections: NamedDomainObjectContainer<Inspection>
    get() = (this as ExtensionAware).extensions.getByName("inspections") as NamedDomainObjectContainer<Inspection>

fun ProjectSettings.inspections(configure: Action<NamedDomainObjectContainer<Inspection>>): Unit =
    (this as ExtensionAware).extensions.configure("inspections", configure)

@Suppress("UNCHECKED_CAST")
val ProjectSettings.ideArtifacts: NamedDomainObjectContainer<TopLevelArtifact>
    get() = (this as ExtensionAware).extensions.getByName("ideArtifacts") as NamedDomainObjectContainer<TopLevelArtifact>

fun ProjectSettings.ideArtifacts(configure: Action<NamedDomainObjectContainer<TopLevelArtifact>>): Unit =
    (this as ExtensionAware).extensions.configure("ideArtifacts", configure)

@Suppress("UNCHECKED_CAST")
val IdeaModule.settings: ModuleSettings
    get() = (this as ExtensionAware).extensions.getByName("settings") as ModuleSettings

fun IdeaModule.settings(configure: Action<ModuleSettings>): Unit =
    (this as ExtensionAware).extensions.configure("settings", configure)

@Suppress("UNCHECKED_CAST")
val IdeaModule.packagePrefix: PackagePrefixContainer
    get() = (this as ExtensionAware).extensions.getByName("packagePrefix") as PackagePrefixContainer

fun IdeaModule.packagePrefix(configure: Action<PackagePrefixContainer>): Unit =
    (this as ExtensionAware).extensions.configure("packagePrefix", configure)

@Suppress("UNCHECKED_CAST")
val IdeaModule.moduleType: ModuleTypesConfig
    get() = (this as ExtensionAware).extensions.getByName("moduleType") as ModuleTypesConfig

fun IdeaModule.moduleType(configure: Action<ModuleTypesConfig>): Unit =
    (this as ExtensionAware).extensions.configure("moduleType", configure)


idea {


    project {
        vcs = "Git"
        jdkName = "jdk11_openjdk"
//        languageLevel = IdeaLanguageLevel("JDK_1_8")
        targetBytecodeVersion = JavaVersion.VERSION_1_8

        settings {
            encodings {
                encoding = "UTF-8"
                bomPolicy = EncodingConfiguration.BomPolicy.WITH_NO_BOM
            }
            doNotDetectFrameworks("android", "web", "javaeeApplication")
            
//            ActionDelegationConfig
        }
    }

    module {
        this.excludes = ".gradle .idea build gradle docs resources/lib"
        this.isDownloadSources = true
        this.isDownloadJavadoc = false
    }
}

tasks.test {
    useJUnit()
}

application {
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=false")
    mainClass.set("fa.market.ApplicationKt")
}

//tasks.register("runDev") {
//    group = "application"
//    description = "Runs the application with the dev profile"
//
//    doFirst {
//        tasks.run.configure {
//            systemProperty("io.ktor.development", "true")
//        }
//    }
//    finalizedBy("run")
//}
