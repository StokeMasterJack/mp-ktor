package fa.market.user

import ss.kutil.Enums
import java.util.*

enum class RoleGroup(val roles: Set<Role>) {
    Admin(roles = setOf(Role.Admin)),
    Bot(roles = setOf(Role.SysBot, Role.TeamBot)),
    Dash(roles = setOf(Role.Admin, Role.Dashboard))
}

enum class Role(val description: String) {

    Admin("Can administer the app"),
    TeamBot("Team created bots: Retailer"),
    SysBot("FastAscension provided bots: Distributor, Customer"),
    Dashboard("Access the dashboard");

    val roleName: String get() = name.uppercase()

    companion object {

        fun parseSafe(name: String): Role = Enums.parseSafe(name)

        /**
         * @param names whitespace eliminated ex: Admin User
         */
        fun parseValuesSafe(names: String): SortedSet<Role> {
            val values: List<Role> = Enums.parseValuesSafe<Role>(names)
            return values.toSortedSet()
        }

    }


}

