package trying.cosmos.domain.user.entity

import javax.persistence.*

@Entity
@DiscriminatorValue("social")
class SocialUser(

    name: String,

    @Enumerated(EnumType.STRING)
    val socialType: SocialType,

    @Column(unique = true)
    val identifier: String

): User(name = name)