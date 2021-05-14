@file:OptIn(ExperimentalSerializationApi::class)

package fa.market.user

import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.Serializable
import kotlinx.serialization.hocon.Hocon
import ss.kutil.CP


@Serializable
internal data class UserList(val users: List<User>)


class UserImporter() {

    private fun parseUsersInternal(configString: String): UserList {
        val deserializer: DeserializationStrategy<UserList> = UserList.serializer()
        val configStringFixed = "users=${configString}"
        val config: Config = ConfigFactory.parseString(configStringFixed)
        val hoconParser = Hocon { useConfigNamingConvention = false }
        return hoconParser.decodeFromConfig(deserializer, config)
    }

    private fun loadUsersResource(usersResourcePath: String): String = CP.readText(usersResourcePath)

    fun parseUsers(configString: String): List<User> {
        val userList: UserList = parseUsersInternal(configString)
        return userList.users
    }

    fun importUsersFromResource(usersResourcePath: String = "users.conf"): List<User> {
        val rawUserData: String = loadUsersResource(usersResourcePath)
        return parseUsers(rawUserData)
    }


}
