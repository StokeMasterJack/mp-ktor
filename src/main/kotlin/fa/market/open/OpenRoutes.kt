@file:Suppress("UNUSED_PARAMETER")

package fa.market.open

import fa.market.KtorUtil
import fa.market.user.UserRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*
import io.ktor.util.PlatformUtils


fun Routing.openRoutes(userRepo: UserRepo) {
    home(userRepo)
    login(userRepo)
    logout(userRepo)
}

private fun Route.home(userRepo: UserRepo) {
    get("/") {
        call.respondText("Home Page: ${KtorUtil.all}")
    }

}

private fun Route.login(userRepo: UserRepo) {
    get("/login") {
        call.respondText("Login")
    }

}

private fun Route.logout(userRepo: UserRepo) {
    get("/logout") {
        call.respondText("Logout")
    }

}