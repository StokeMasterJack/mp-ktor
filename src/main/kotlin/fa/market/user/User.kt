package fa.market.user

import kotlinx.serialization.Serializable


@Serializable
data class User(
    val username: UserName,
    val password: EncodedPassword,
    val roles: List<Role>,
    val enabled: Boolean = true
)

