package trying.cosmos.domain.certification.service

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import trying.cosmos.domain.certification.configuration.CertificationConfig
import trying.cosmos.domain.certification.entity.Certification
import trying.cosmos.domain.certification.repository.CertificationRepository
import trying.cosmos.domain.user.repository.UserRepository
import trying.cosmos.global.email.EmailSender
import trying.cosmos.global.email.EmailTemplate
import trying.cosmos.global.extension.generateRandomString

@Service
@Transactional(readOnly = true)
class CertificationService(

    private val config: CertificationConfig,

    private val certificationRepository: CertificationRepository,

    private val userRepository: UserRepository,

    private val emailSender: EmailSender

) {

    @Transactional
    fun generate(email: String) {
        if (userRepository.existsByEmail(email)) throw RuntimeException("email already exist")
        val certification: Certification =
            certificationRepository.findByEmail(email)?.replace(generateRandomString(config.codeLength), config.expiredTime)
            ?: certificationRepository.save(Certification(generateRandomString(config.codeLength), email, config.expiredTime))
        emailSender.send(
            template = EmailTemplate.CERTIFICATION_CODE,
            to = email,
            model = mapOf(
                "code" to certification.code
            )
        )
    }

    @Transactional
    fun certificate(email: String, code: String) {
        certificationRepository.findByEmail(email)?.certificate(code) ?: throw RuntimeException("certification is not exist")
    }
}