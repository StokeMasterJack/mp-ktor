package ss.kutil

val Any.simpleName: String get() = this::class.simpleName!!
val Any.lname: String get() = simpleName.lowercase()
val Any.uname: String get() = simpleName.uppercase()
