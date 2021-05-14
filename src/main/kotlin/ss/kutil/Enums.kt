package ss.kutil


object Enums {

    /**
     * ignoreCase and whitespace
     */
    inline fun <reified E : Enum<E>> parseSafe(name: String): E {
        val uname = name.trim().uppercase()
        val values: Array<E> = enumValues<E>()
        return values.first { it.uname == uname }
    }


    /**
     * @param roleNames whitespace eliminated ex: Admin User
     */
    inline fun <reified E : Enum<E>> parseValuesSafe(names: String): List<E> {
        val a1 = names.trim().splitOnWhitespace()
        val a2 = a1.filter { it.isNotBlank() }
        return a2.map { parseSafe<E>(it) }.toList()
    }

}




