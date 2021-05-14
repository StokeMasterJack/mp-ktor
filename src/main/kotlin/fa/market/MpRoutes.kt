@file:OptIn(KtorExperimentalLocationsAPI::class)

package fa.market

import io.ktor.application.*
import io.ktor.http.*
import io.ktor.locations.*
import io.ktor.response.*
import kotlinx.css.CSSBuilder


class AuthenticationException : RuntimeException()
class AuthorizationException : RuntimeException()

/**
 * Location for login a [userName] with a [password].
 */
@Location("/login")
data class Login(val userName: String = "", val password: String = "")

@Location("/location/{name}")
class MyLocation(val name: String, val arg1: Int = 42, val arg2: String = "default")

@Location("/type/{name}")
data class Type(val name: String) {
    @Location("/edit")
    data class Edit(val type: Type)

    @Location("/list/{page}")
    data class List(val type: Type, val page: Int)
}


suspend inline fun ApplicationCall.respondCss(builder: CSSBuilder.() -> Unit) {
    this.respondText(CSSBuilder().apply(builder).toString(), ContentType.Text.CSS)
}


