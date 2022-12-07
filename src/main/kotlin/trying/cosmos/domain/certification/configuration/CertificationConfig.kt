package trying.cosmos.domain.certification.configuration

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "certification")
class CertificationConfig(

    val expiredTime: Long,

    val codeLength: Int

)