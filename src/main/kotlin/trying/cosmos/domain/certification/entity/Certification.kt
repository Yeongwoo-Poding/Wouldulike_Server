package trying.cosmos.domain.certification.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Certification(

    var code: String,

    val email: String,

    private var expiredTime: Long,

    var isCertified: Boolean = false,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id")
    val id: Long? = null

) {
    private val expiredDate: LocalDateTime = LocalDateTime.now().plusSeconds(expiredTime)

    fun replace(code: String, expiredTime: Long): Certification {
        this.code = code
        this.expiredTime = expiredTime
        return this
    }

    fun certificate(code: String) {
        if (isExpired()) throw RuntimeException("Certification is expired")
        if (this.code != code) throw RuntimeException("Wrong certificate code")
        this.isCertified = true
    }

    private fun isExpired(): Boolean = this.expiredDate.isBefore(LocalDateTime.now())
}