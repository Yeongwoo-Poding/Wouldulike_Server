package trying.cosmos.domain.user.dto.request

data class EmailLoginRequest(
    val email: String,
    val password: String,
    val pushToken: String
)
