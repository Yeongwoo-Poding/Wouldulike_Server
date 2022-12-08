package trying.cosmos.domain.user.dto

data class EmailJoinRequest(
    val email: String,
    val password: String,
    val name: String,
    val pushToken: String
)
