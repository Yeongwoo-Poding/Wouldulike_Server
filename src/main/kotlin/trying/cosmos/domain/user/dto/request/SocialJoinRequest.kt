package trying.cosmos.domain.user.dto.request

import trying.cosmos.domain.common.annotation.Username

data class SocialJoinRequest(

    val identifier: String,

    @field:Username
    val name: String,

    val pushToken: String

)
