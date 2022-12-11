package trying.cosmos.domain.user.dto.response

import com.fasterxml.jackson.annotation.JsonInclude

@JsonInclude(JsonInclude.Include.NON_NULL)
data class UserInfoResponse(
    val id: Long,
    val name: String,
    val loginBy: String,
    val email: String?
)
