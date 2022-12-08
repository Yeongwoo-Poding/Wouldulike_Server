package trying.cosmos.domain.user.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.user.dto.UserLoginResponse
import trying.cosmos.domain.user.entity.SocialType
import trying.cosmos.domain.user.entity.SocialUser
import trying.cosmos.domain.user.repository.UserRepository

@Service
@Transactional(readOnly = true)
class SocialAuthenticationService(

    private val userRepository: UserRepository,

    private val sessionService: SessionService

) {

    @Transactional
    fun join(name: String, socialType: SocialType, identifier: String, pushToken: String): UserLoginResponse {
        if (userRepository.existsByIdentifier(identifier)) throw RuntimeException("Identifier already exist")
        val user = userRepository.save(SocialUser(name, socialType, identifier))
        return UserLoginResponse(sessionService.create(user))
    }

    @Transactional
    fun login(socialType: SocialType, identifier: String, pushToken: String): UserLoginResponse {
        val user = userRepository.findByIdentifier(identifier) ?: throw RuntimeException("User not exist")
        return UserLoginResponse(sessionService.create(user))
    }
}