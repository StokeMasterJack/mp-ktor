package fa.market.admin

import fa.market.user.UserRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.adminRoutes(userRepo: UserRepo) {
    route("/admin") {
        resetUserDatabase(userRepo)
    }
}

private fun Route.resetUserDatabase(userRepo: UserRepo) {
    get("/resetUserDatabase") {
        userRepo.resetUserDatabase()
        call.respondText("resetUserDatabase Complete!")
    }

}