package trying.cosmos.domain.certification.controller

import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import trying.cosmos.domain.certification.dto.CertificationCertificateRequest
import trying.cosmos.domain.certification.dto.CertificationGenerateRequest
import trying.cosmos.domain.certification.service.CertificationService

@RestController
@RequestMapping("/certifications")
class CertificationController(

    private val certificateService: CertificationService

) {

    @PostMapping("/generate")
    fun generate(@RequestBody request: CertificationGenerateRequest) = certificateService.generate(request.email)

    @PostMapping("/certificate")
    fun certificate(@RequestBody request: CertificationCertificateRequest) = certificateService.certificate(request.email, request.code)
}