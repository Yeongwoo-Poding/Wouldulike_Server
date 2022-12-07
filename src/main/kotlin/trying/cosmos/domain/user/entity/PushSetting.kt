package trying.cosmos.domain.user.entity

import javax.persistence.*

@Entity
class PushSetting(

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User,

    var enableGlobalNotification: Boolean = false,

    var enableCourseNotification: Boolean = true,

    var enableReviewNotification: Boolean = true,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "push_setting_id")
    val id: Long? = null

)