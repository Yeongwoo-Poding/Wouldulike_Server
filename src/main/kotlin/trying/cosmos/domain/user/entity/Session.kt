package trying.cosmos.domain.user.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.*
import javax.persistence.Column
import javax.persistence.Id

@RedisHash("session")
class Session(

    @Indexed
    @Column(nullable = false)
    val userId: Long,

    @Column(nullable = false)
    val authorityType: AuthorityType,

    @Id
    @Column(name = "session_id", nullable = false)
    val id: String = UUID.randomUUID().toString()

)