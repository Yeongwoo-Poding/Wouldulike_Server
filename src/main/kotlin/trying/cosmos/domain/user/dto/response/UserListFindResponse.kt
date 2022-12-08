package trying.cosmos.domain.user.dto.response

data class UserListFindResponse(
    val contents: List<UserFindResponse>,
    val page: Int,
    val hasNext: Boolean
)