package ss.kutil

fun String.splitOnWhitespace() = this.split("\\s".toRegex())

@JvmInline
value class Str3(val v: String)
