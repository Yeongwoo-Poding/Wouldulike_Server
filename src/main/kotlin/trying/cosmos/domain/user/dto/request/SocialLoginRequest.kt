package trying.cosmos.domain.user.dto.request

import trying.cosmos.domain.user.entity.SocialType

data class SocialLoginRequest(
    val socialType: SocialType,
    val identifier: String,
    val pushToken: String
)
