package fa.market.open

//import kotlinx.css as xxx

import fa.market.common.FastHtmlDoc
import fa.market.common.Session
import kotlinx.html.*
import ss.kutil.Sys

class LoginPage(session: Session) : FastHtmlDoc(session) {

    override val pageTitle: String = "Login"

    override fun HtmlBlockTag.buildPage() {
        form {
            action = "/login"
            method = FormMethod.post
            autoComplete = false


            table {
                tr {
                    td {
                        +"User: "
                    }
                    td {
                        input(type = InputType.text, name = "username") {
                            value = if (Sys.isDavesMac) "david.ford" else ""
                        }
                    }
                }
                tr {
                    td {
                        +"Password: "
                    }
                    td {
                        input(type = InputType.password, name = "password") {
                            value = if (Sys.isDavesMac) "david.ford-pw" else ""
                        }
                    }
                }
                tr {
                    td {
                        colSpan = "2"
//                        css {
//                            textAlign = TextAlign.center
//                        }
                        input(type = InputType.submit, name = "submit") {
                            value = "submit"
                        }
                    }
                }
            }
        }
    }

}



