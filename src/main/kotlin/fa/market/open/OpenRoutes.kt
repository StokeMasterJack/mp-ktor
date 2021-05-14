package fa.market.open

import fa.market.user.UserRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*


fun Routing.openRoutes(userRepo: UserRepo) {
    home(userRepo)
    login(userRepo)
    logout(userRepo)
}

private fun Route.home(userRepo: UserRepo) {
    get("/") {
        call.respondText("Home Page")
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