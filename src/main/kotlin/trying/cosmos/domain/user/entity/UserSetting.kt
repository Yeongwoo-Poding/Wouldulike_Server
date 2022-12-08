package trying.cosmos.domain.user.entity

import javax.persistence.*

@Entity
class UserSetting(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    var enableGlobalNotification: Boolean = false,

    var enableCourseNotification: Boolean = true,

    var enableReviewNotification: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_setting_id")
    val id: Long? = null

)