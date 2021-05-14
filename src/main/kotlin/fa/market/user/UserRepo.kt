package fa.market.user

//typealias AuthTable = Map<String, ByteArray>

class UserRepo(
    private val map: MutableMap<UserName, User> = mutableMapOf(),
    private val importer: UserImporter = UserImporter()
) {

    private fun importUsersFromResource() {
        val users: List<User> = importer.importUsersFromResource()
        users.associateByTo(map) { it.username }
    }

    private fun deleteAll() {
        map.clear()
    }

    fun resetUserDatabase() {
        deleteAll()
        importUsersFromResource()
    }

    /**
     * Map<Email, EncodedPassword>
     */
//    fun authTable(): AuthTable = map.mapValues { it.value.password }

    fun count(): Int = map.size

}