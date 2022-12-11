package trying.cosmos.domain.user.entity

import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("email")
class EmailUser(

    name: String,

    @Column(unique = true, nullable = false)
    val email: String,

    @Column(nullable = false)
    var password: String

): User(name = name)