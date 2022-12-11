package trying.cosmos.domain.user.entity

import javax.persistence.*

@Entity
@DiscriminatorValue("social")
class SocialUser(

    name: String,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val socialType: SocialType,

    @Column(unique = true, nullable = false)
    val identifier: String

): User(name = name)