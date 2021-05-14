package ss.html

import kotlinx.css.CSSBuilder
import kotlinx.css.RuleContainer
import kotlinx.html.CommonAttributeGroupFacade
import kotlinx.html.style

val CSSBuilder.serInline: String get() = toString().filter { it != '\n' }
val CSSBuilder.ser: String get() = toString()

fun buildCss(
    indent: String = "",
    allowClasses: Boolean = true,
    parent: RuleContainer? = null,
    block: CSSBuilder.() -> Unit
): CSSBuilder = CSSBuilder(indent, allowClasses, parent).apply(block)

fun buildStyle(
    indent: String = "",
    allowClasses: Boolean = true,
    parent: RuleContainer? = null,
    block: CSSBuilder.() -> Unit
): String = buildCss(indent, allowClasses, parent, block).serInline

fun CommonAttributeGroupFacade.css(block: CSSBuilder.() -> Unit) {
    style = buildCss(block = block).serInline
}



