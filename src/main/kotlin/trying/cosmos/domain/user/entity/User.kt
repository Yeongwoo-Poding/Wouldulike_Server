package trying.cosmos.domain.user.entity

import org.hibernate.annotations.SQLDelete
import org.hibernate.annotations.Where
import trying.cosmos.domain.common.TimeEntity
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "account_type")
@Table(name = "users")
@SQLDelete(sql = "UPDATE users SET deleted_at = NOW() WHERE user_id = ?")
@Where(clause = "deleted_at IS NULL")
abstract class User(

    var name: String,

    @Enumerated(EnumType.STRING)
    val authority: AuthorityType = AuthorityType.USER,

    var deletedAt: LocalDateTime? = null,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    val _id: Long? = null

): TimeEntity() {

    val id: Long
        get() = this._id ?: throw RuntimeException("User id is null")

}