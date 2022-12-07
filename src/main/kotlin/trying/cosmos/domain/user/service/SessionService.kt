package trying.cosmos.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.user.entity.Session
import trying.cosmos.domain.user.entity.User
import trying.cosmos.domain.user.repository.SessionRepository

@Service
@Transactional(readOnly = true)
class SessionService(

    private val sessionRepository: SessionRepository

) {

    @Transactional
    fun create(user: User): Session = sessionRepository.save(Session(user.id ?: throw RuntimeException("userId is null"), user.authority))

    fun find(sessionId: String): Session? = sessionRepository.findByIdOrNull(sessionId)

    @Transactional
    fun delete(sessionId: String) = sessionRepository.deleteById(sessionId)
}