@file:OptIn(ExperimentalSerializationApi::class)

package fa.market.user


import io.ktor.application.Application
import kotlinx.serialization.ExperimentalSerializationApi
import mu.KotlinLogging
import kotlin.test.Test
import kotlin.test.assertEquals



class UserImportTest {
    companion object{
        private val logger = KotlinLogging.logger {}
    }
    private val expected = "[User(username=ahmed.sorour, password=ahmed.sorour-pw, roles=[Admin], enabled=true), User(username=david.ford, password=david.ford-pw, roles=[Admin, SysBot], enabled=true), User(username=jason.king, password=jason.king-pw, roles=[Admin], enabled=true), User(username=kevin.mcmanus, password=kevin.mcmanus-pw, roles=[Admin], enabled=true), User(username=leeanne.mcmanus, password=leeanne.mcmanus-pw, roles=[Admin], enabled=true), User(username=michael.kelly, password=michael.kelly-pw, roles=[Admin], enabled=true), User(username=team1, password=team1-pw, roles=[TeamBot, Dashboard], enabled=true), User(username=team2, password=team2-pw, roles=[TeamBot], enabled=true), User(username=team3, password=team3-pw, roles=[TeamBot], enabled=true), User(username=zbot, password=zbot-pw, roles=[SysBot], enabled=true)]"
    private val userImporter: UserImporter = UserImporter()

    @Test
    fun test_parseUsers() {
        val configString = """
[
    {username = ahmed.sorour, password = ahmed.sorour-pw, roles = [Admin]},
    {username = david.ford, password = david.ford-pw, roles = [Admin,SysBot]},
    {username = jason.king, password = jason.king-pw, roles = [Admin]},
    {username = kevin.mcmanus, password = kevin.mcmanus-pw, roles = [Admin]},
    {username = leeanne.mcmanus, password = leeanne.mcmanus-pw, roles = [Admin]},
    {username = michael.kelly, password = michael.kelly-pw, roles = [Admin]},
    {username = team1, password = team1-pw, roles = [TeamBot,Dashboard]},
    {username = team2, password = team2-pw, roles = [TeamBot]},
    {username = team3, password = team3-pw, roles = [TeamBot]},
    {username = zbot, password = zbot-pw, roles = [SysBot]}
]""".trimIndent()

        val users: List<User> = userImporter.parseUsers(configString)
        val actual = users.toString()
        assertEquals(expected, actual)
        logger.warn { "test_parseUsers success" }
    }

    @Test
    fun test_importUsers() {
        val users = userImporter.importUsersFromResource()
        val actual = users.toString()
        assertEquals(expected, actual)
        println(111)
        
    }
    
    
    fun test() {
//        val xx = Application::class.java
        
    }


}

