package ss.kutil


class ObjectNotFoundException(typeName: String, id: Any) : RuntimeException("$typeName/$id")

fun err(msg: String? = "IllegalState"): Nothing = throw IllegalStateException(msg)

val err: Nothing = err()