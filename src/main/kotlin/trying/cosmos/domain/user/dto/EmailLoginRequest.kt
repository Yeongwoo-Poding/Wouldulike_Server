package trying.cosmos.domain.user.dto

data class EmailLoginRequest(
    val email: String,
    val password: String,
    val pushToken: String
)
