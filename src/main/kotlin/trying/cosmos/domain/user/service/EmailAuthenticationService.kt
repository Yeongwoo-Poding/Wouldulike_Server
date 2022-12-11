package trying.cosmos.domain.user.service

import org.mindrot.jbcrypt.BCrypt
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.certification.repository.CertificationRepository
import trying.cosmos.domain.user.dto.response.UserExistResponse
import trying.cosmos.domain.user.dto.response.UserLoginResponse
import trying.cosmos.domain.user.entity.EmailUser
import trying.cosmos.domain.user.repository.UserRepository
import trying.cosmos.global.email.EmailSender
import trying.cosmos.global.email.EmailTemplate
import trying.cosmos.global.extension.generateRandomString

@Service
@Transactional(readOnly = true)
class EmailAuthenticationService(

    private val userRepository: UserRepository,

    private val certificationRepository: CertificationRepository,

    private val sessionService: SessionService,

    private val emailSender: EmailSender

) {

    fun isExist(email: String): UserExistResponse = UserExistResponse(userRepository.existsByEmail(email))

    @Transactional
    fun join(name: String, email: String, password: String, pushToken: String): UserLoginResponse {
        val certification = certificationRepository.findByEmail(email) ?: throw RuntimeException("Certification is not exist")
        if (!certification.isCertified) throw RuntimeException("Certification is not certified")
        certificationRepository.delete(certification)
        val user = userRepository.save(EmailUser(name, email, BCrypt.hashpw(password, BCrypt.gensalt())))
        return UserLoginResponse(user.id, sessionService.create(user))
    }

    @Transactional
    fun login(email: String, password: String, pushToken: String): UserLoginResponse {
        val user = userRepository.findByEmail(email) ?: throw RuntimeException("User is not exist")
        if (!user.isMatch(password)) throw RuntimeException("Wrong password")
        return UserLoginResponse(user.id, sessionService.create(user))
    }

    @Transactional
    fun resetPassword(email: String) {
        val user = userRepository.findByEmail(email) ?: throw RuntimeException("User not exist")
        val password = generateRandomString(8)
        (user as? EmailUser)?.password = BCrypt.hashpw(password, BCrypt.gensalt()) ?: throw RuntimeException("This user is Social user")
        emailSender.send(
            template = EmailTemplate.RESET_PASSWORD,
            to = email,
            model = mapOf(
                "code" to password
            )
        )
    }
}