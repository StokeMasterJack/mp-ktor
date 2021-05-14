@file:OptIn(KtorExperimentalLocationsAPI::class)

package fa.market

import fa.market.user.UserRepo
import io.ktor.application.*
import io.ktor.features.*
import io.ktor.http.*
import io.ktor.http.content.*
import io.ktor.locations.*
import io.ktor.request.*
import io.ktor.serialization.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import org.slf4j.event.Level

val Application.userRepo: UserRepo by lazy { UserRepo() }

fun main() {

    embeddedServer(Netty, port = 9090) {

        install(ContentNegotiation) {
            json()
        }

        install(CachingHeaders) {
            options { outgoingContent ->
                when (outgoingContent.contentType?.withoutParameters()) {
                    ContentType.Text.CSS -> CachingOptions(CacheControl.MaxAge(maxAgeSeconds = 24 * 60 * 60))
                    else -> null
                }
            }
        }


        install(DefaultHeaders) {
            header("X-Engine", "Ktor") // will send this header with each response
        }

        install(CallLogging) {
            level = Level.INFO
            filter { call -> call.request.path().startsWith("/") }
        }

        install(Locations)

//        configureSecurity()


        routes()

        install(ShutDownUrl.ApplicationCallFeature) {
            // The URL that will be intercepted (you can also use the application.conf's ktor.deployment.shutdown.url key)
            shutDownUrl = "/ktor/application/shutdown"
            // A function that will be executed to get the exit code of the process
            exitCodeSupplier = { 0 } // ApplicationCall.() -> Int
        }

    }.start(wait = true)


}


fun f1() {
//    io.ktor.st
}
