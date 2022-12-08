package trying.cosmos.domain.user.dto

import trying.cosmos.domain.user.entity.SocialType

data class SocialLoginRequest(
    val socialType: SocialType,
    val identifier: String,
    val pushToken: String
)
