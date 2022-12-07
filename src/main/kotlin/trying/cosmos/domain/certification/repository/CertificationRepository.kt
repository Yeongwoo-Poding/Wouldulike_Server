package trying.cosmos.domain.certification.repository

import org.springframework.data.jpa.repository.JpaRepository
import trying.cosmos.domain.certification.entity.Certification

interface CertificationRepository: JpaRepository<Certification, Long> {

    fun findByEmail(email: String): Certification?
}