package trying.cosmos.domain.user.dto.request

data class EmailJoinRequest(
    val email: String,
    val password: String,
    val name: String,
    val pushToken: String
)
