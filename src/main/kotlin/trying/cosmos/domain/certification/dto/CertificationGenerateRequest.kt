package trying.cosmos.domain.certification.dto

import javax.validation.constraints.Email

data class CertificationGenerateRequest(

    @field:Email
    val email: String

)
