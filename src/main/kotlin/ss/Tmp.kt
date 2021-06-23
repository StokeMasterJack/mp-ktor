package ss

import io.ktor.server.engine.applicationEngineEnvironment
import io.ktor.server.engine.connector
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import org.slf4j.LoggerFactory

fun main() {
    embeddedServer(Netty, environment = applicationEngineEnvironment {
        
        log = LoggerFactory.getLogger("ktor.application")
        
        this.developmentMode = true
        

        module {
            main()
        }

        connector {
            port = 8080
            host = "127.0.0.1"
        }
    }).start(true)
}