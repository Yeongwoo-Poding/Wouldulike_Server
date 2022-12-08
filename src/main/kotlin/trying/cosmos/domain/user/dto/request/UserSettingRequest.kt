package trying.cosmos.domain.user.dto.request

data class UserSettingRequest(
    val enableGlobalNotification: Boolean,
    val enableCourseNotification: Boolean,
    val enableReviewNotification: Boolean
)