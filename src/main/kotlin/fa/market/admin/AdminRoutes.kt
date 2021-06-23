package fa.market.admin

import fa.market.user.User
import fa.market.user.UserRepo
import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.*

fun Routing.adminRoutes(userRepo: UserRepo) {

    route("/admin") {
        resetUserDatabase(userRepo)
        users(userRepo)
    }

}


//   http://localhost:9090/admin/resetUserDatabase
private fun Route.resetUserDatabase(userRepo: UserRepo) {
    get("/resetUserDatabase") {
        userRepo.resetUserDatabase()
        call.respondText("resetUserDatabase Complete!")
    }

}


//   http://localhost:9090/admin/users.json
private fun Route.users(userRepo: UserRepo) {
    get("/users.json") {
        val usersIterable: Iterable<User> = userRepo.findAll()
        val list = usersIterable.toList()
        call.respond(list)
    }
}