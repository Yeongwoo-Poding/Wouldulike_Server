package trying.cosmos.domain.user.dto

import trying.cosmos.domain.user.entity.SocialType

data class SocialJoinRequest(
    val socialType: SocialType,
    val identifier: String,
    val name: String,
    val pushToken: String
)
