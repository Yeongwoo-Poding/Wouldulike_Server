package trying.cosmos.domain.user.dto

data class UserSettingResponse(
    val enableGlobalNotification: Boolean,
    val enableCourseNotification: Boolean,
    val enableReviewNotification: Boolean
)
