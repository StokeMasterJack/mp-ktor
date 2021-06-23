package fa.market.common

//import org.springframework.security.authentication.AnonymousAuthenticationToken
//import org.springframework.security.core.context.SecurityContextHolder
//import org.springframework.security.core.userdetails.UserDetails
import fa.market.user.User
import kotlinx.css.*
import kotlinx.html.*
import kotlinx.html.stream.appendHTML
import ss.html.css


abstract class FastHtmlDoc(private val session: Session) {


    abstract val pageTitle: String

    abstract fun HtmlBlockTag.buildPage()

    fun render(): String {
        return buildString {
            renderHtmlDoc()
        }
    }

    private fun <A : Appendable> A.renderHtmlDoc() {
        appendLine("<!DOCTYPE html>")
        appendHTML().html(namespace = "http://www.w3.org/1999/xhtml") {
            lang = "en"
            renderHtmlDocContent()
        }
    }

    private fun HTML.renderHtmlDocContent() {
        renderHead()
        renderBody()
    }

    private fun HTML.renderHead() {
        head {
            meta(charset = "UTF-8")
            link(href = "/index.css", rel = "stylesheet")
            link(href = "/index.robo.css", rel = "stylesheet")
            title {
                +"$pageTitle Fast Ascension"
            }
            
        }
    }

    private fun HTML.renderBody() {
        val user: User? = session.getCurrentUser()
        body {
            css {
                margin(0.px)
                padding(0.px)
            }
            div {
                a {  }
                h1{  +"" }
                
                css {
                    display = Display.flex
                    flexDirection = FlexDirection.row
                    justifyContent = JustifyContent.spaceBetween
                    alignItems = Align.center
                    height = 50.px
                    padding(10.px)
                    backgroundColor = Color("yellow")
                    color = Color("black")
                }

                div { a("/open/home.html") { +"Fast Ascension" } }

                div { +pageTitle }


                if (user == null) {
                    div { a("/login") { +"Login" } }
                } else {
                    div {
                        div { +user.username }
                        div { a("/logout") { +"Logout" } }
                    }
                }

                

            }
            div {
                css {
                    padding(20.px)
                }

                buildPage()

            }

        }
    }


}



