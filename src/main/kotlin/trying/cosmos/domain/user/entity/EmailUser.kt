package trying.cosmos.domain.user.entity

import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("email")
class EmailUser(

    name: String,

    val email: String,

    var password: String): User(name = name) {

}