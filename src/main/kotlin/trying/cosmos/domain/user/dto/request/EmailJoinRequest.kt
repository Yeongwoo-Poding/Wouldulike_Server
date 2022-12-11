package trying.cosmos.domain.user.dto.request

import trying.cosmos.domain.common.annotation.Password
import trying.cosmos.domain.common.annotation.Username
import javax.validation.constraints.Email

data class EmailJoinRequest(

    @field:Email
    val email: String,

    @field:Password
    val password: String,

    @field:Username
    val name: String,

    val pushToken: String

)
