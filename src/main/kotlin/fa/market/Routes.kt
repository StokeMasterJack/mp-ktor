package fa.market

import fa.market.admin.adminRoutes
import fa.market.open.openRoutes
import io.ktor.application.*
import io.ktor.routing.*

fun Application.routes() {

    routing {
        openRoutes(userRepo)
        adminRoutes(userRepo)
    }

}

