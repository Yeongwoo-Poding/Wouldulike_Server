package trying.cosmos.domain.user.dto.request

data class SocialLoginRequest(
    val identifier: String,
    val pushToken: String
)
