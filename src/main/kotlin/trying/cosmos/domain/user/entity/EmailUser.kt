package trying.cosmos.domain.user.entity

import org.mindrot.jbcrypt.BCrypt
import javax.persistence.Column
import javax.persistence.DiscriminatorValue
import javax.persistence.Entity

@Entity
@DiscriminatorValue("email")
class EmailUser(

    name: String,

    @Column(unique = true)
    val email: String,

    var password: String

): User(name = name) {

    fun isMatch(password: String): Boolean = BCrypt.checkpw(password, this.password)
}