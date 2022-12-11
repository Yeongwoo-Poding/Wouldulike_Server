package trying.cosmos.domain.user.entity

import javax.persistence.*

@Entity
class UserSetting(

    @Column(nullable = false)
    var enableGlobalNotification: Boolean = false,

    @Column(nullable = false)
    var enableCourseNotification: Boolean = true,

    @Column(nullable = false)
    var enableReviewNotification: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_setting_id")
    val id: Long? = null

)