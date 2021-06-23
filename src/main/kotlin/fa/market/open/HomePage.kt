package fa.market.open

//import kotlinx.css as xxx

import fa.market.common.FastHtmlDoc
import fa.market.common.Session
import fa.market.user.RoleGroup
import kotlinx.css.BorderCollapse
import kotlinx.css.BorderStyle
import kotlinx.css.Color
import kotlinx.css.borderCollapse
import kotlinx.css.properties.border
import kotlinx.css.px
import kotlinx.css.width
import kotlinx.html.HtmlBlockTag
import kotlinx.html.HtmlInlineTag
import kotlinx.html.TABLE
import kotlinx.html.TR
import kotlinx.html.a
import kotlinx.html.div
import kotlinx.html.table
import kotlinx.html.td
import kotlinx.html.th
import kotlinx.html.tr
import ss.html.css


class HomePage(session: Session = Session()) : FastHtmlDoc(session) {
    data class Link(
        val path: String,
        val text: String = path,
        val roleGroup: RoleGroup? = null
    )

    private val links: List<Link> = listOf(
        Link("/"),
        Link("/open/hello.json"),
        Link("/open/home.html"),

        Link("/admin/hello.json", roleGroup = RoleGroup.Admin),
        Link("/admin/users.json", roleGroup = RoleGroup.Admin),
        Link("/admin/users.html", roleGroup = RoleGroup.Admin),
        Link("/admin/user.html", roleGroup = RoleGroup.Admin),
        Link("/admin/roles.html", roleGroup = RoleGroup.Admin),

        Link("/customer/hello.json", roleGroup = RoleGroup.Bot),
        Link("/distributor/hello.json", roleGroup = RoleGroup.Bot),
        Link("/dashboard/hello.json", roleGroup = RoleGroup.Dash),

        Link("/login"),
        Link("/logout")
    )

    override val pageTitle: String = "Home Page"

    override fun HtmlBlockTag.buildPage() {
        div {

            table {
                css {
                    border(width = 1.px, color = Color.black, style = BorderStyle.solid)
                    borderCollapse = BorderCollapse.collapse
                }
                linkThRow()
                links.forEach {
                    linkTdRow(it)
                }

            }


        }
    }


    private fun TABLE.linkThRow() {
        tr {
            th {
                css { border(width = 1.px, color = Color.black, style = BorderStyle.solid) }
                +"Path"
            }
            th {
                css { border(width = 1.px, color = Color.black, style = BorderStyle.solid) }
                +"Roles"
            }
        }
    }

    //    EndPoint("/admin/**", "Admin", "/admin/hello"),
    private fun TABLE.linkTdRow(link: Link) {
        tr {
            td1 { aa(link) }
            td1 {
                if (link.roleGroup == null) {
                    +" "
                } else {
                    +link.roleGroup.roles.sortedBy { it.roleName }.joinToString(separator = " or ")
                }
            }
        }
    }

    private fun TR.th1(block: HtmlInlineTag.() -> Unit) {
        th {
            css {
                border(width = 1.px, color = Color.black, style = BorderStyle.solid)
                width = 300.px
            }
            block()
        }
    }

    private fun TR.td1(block: HtmlBlockTag.() -> Unit) {
        td {
            css {
                border(width = 1.px, color = Color.black, style = BorderStyle.solid)
                width = 300.px
            }
            block()
        }
    }

    private fun HtmlBlockTag.aa(link: Link) {
        aa(href = link.path, text = link.text)
    }

    private fun HtmlBlockTag.aa(href: String, text: String = href) {
        div { a(href) { +text } }
    }

}



