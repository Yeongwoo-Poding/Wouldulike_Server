package trying.cosmos.domain.user.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated

@Entity
@DiscriminatorValue("social")
class SocialUser(

    name: String,

    @Enumerated(EnumType.STRING)
    val socialType: SocialType,

    val identifier: String

): User(name = name)