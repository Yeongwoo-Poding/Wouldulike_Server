package trying.cosmos.domain.user.dto.request

data class SocialJoinRequest(
    val identifier: String,
    val name: String,
    val pushToken: String
)
