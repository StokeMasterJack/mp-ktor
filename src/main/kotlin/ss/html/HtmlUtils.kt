package ss.html

import kotlinx.css.Display
import kotlinx.css.FlexDirection
import kotlinx.css.LinearDimension
import kotlinx.css.display
import kotlinx.css.flexDirection
import kotlinx.css.marginBottom
import kotlinx.css.marginRight
import kotlinx.css.px
import kotlinx.html.DIV
import kotlinx.html.FlowContent
import kotlinx.html.HtmlTagMarker
import kotlinx.html.div

@HtmlTagMarker
fun FlowContent.row(block: DIV.() -> Unit = {}) {
    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.row
        }
        block()
    }
}

@HtmlTagMarker
fun FlowContent.col(block: DIV.() -> Unit = {}) {
    div {
        css {
            display = Display.flex
            flexDirection = FlexDirection.column
        }
        block()
    }
}


fun FlowContent.vsp(size: LinearDimension = 10.px) {
    div {
        css {
            marginBottom = size
        }
    }
}

fun FlowContent.hsp(size: LinearDimension = 10.px) {
    div {
        css {
            marginRight = size
        }
    }
}


