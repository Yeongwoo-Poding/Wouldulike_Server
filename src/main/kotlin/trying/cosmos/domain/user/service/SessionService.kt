package trying.cosmos.domain.user.service

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.user.entity.Session
import trying.cosmos.domain.user.entity.User
import trying.cosmos.domain.user.repository.SessionRepository
import trying.cosmos.global.authentication.TokenProvider

@Service
@Transactional(readOnly = true)
class SessionService(

    private val sessionRepository: SessionRepository,

    private val tokenProvider: TokenProvider

) {

    @Transactional
    fun create(user: User): String = tokenProvider.createAccessToken(sessionRepository.save(Session(user.id, user.authority)))

    fun find(sessionId: String): Session? = sessionRepository.findByIdOrNull(sessionId)

    @Transactional
    fun delete(sessionId: String) = sessionRepository.deleteById(sessionId)

    @Transactional
    fun deleteByUserId(userId: Long) = sessionRepository.deleteAllByUserId(userId)
}