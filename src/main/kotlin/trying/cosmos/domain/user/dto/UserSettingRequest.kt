package trying.cosmos.domain.user.dto

data class UserSettingRequest(
    val enableGlobalNotification: Boolean,
    val enableCourseNotification: Boolean,
    val enableReviewNotification: Boolean
)