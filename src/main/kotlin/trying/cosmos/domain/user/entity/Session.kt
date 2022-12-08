package trying.cosmos.domain.user.entity

import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.index.Indexed
import java.util.*
import javax.persistence.Column
import javax.persistence.Id

@RedisHash("session")
class Session(

    @Indexed
    val userId: Long,

    val authorityType: AuthorityType,

    @Id
    @Column(name = "session_id")
    val id: String = UUID.randomUUID().toString()

)