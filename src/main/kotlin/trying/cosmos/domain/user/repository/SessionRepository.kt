package trying.cosmos.domain.user.repository

import org.springframework.data.repository.CrudRepository
import trying.cosmos.domain.user.entity.Session

interface SessionRepository: CrudRepository<Session, String> {

    fun findAllByUserId(userId: Long): List<Session>

}