package trying.cosmos.domain.user.dto.request

import trying.cosmos.domain.common.annotation.Password
import trying.cosmos.domain.common.annotation.Username

data class UserUpdateRequest(

    @field:Username
    val name: String?,

    @field:Password
    val password: String?

)