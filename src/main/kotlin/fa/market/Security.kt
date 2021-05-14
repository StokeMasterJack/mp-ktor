@file:OptIn(KtorExperimentalLocationsAPI::class)

package fa.market

import io.ktor.auth.*
import io.ktor.locations.*
import io.ktor.application.*


fun Application.configureSecurity() {


//    val myFormAuthentication = "myFormAuthentication"

    install(Authentication) {

//        form(myFormAuthentication) {
//            userParamName = Login::userName.name
//            passwordParamName = Login::password.name
//            challenge {
//
//                call.respondRedirect(call.url(Login(it?.name ?: "")))
//            }
//
//            validate {
//
//                users.authenticate(it)
//
//            }
//        }
    }


}
