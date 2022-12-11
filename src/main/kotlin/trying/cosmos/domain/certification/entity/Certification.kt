package trying.cosmos.domain.certification.entity

import java.time.LocalDateTime
import javax.persistence.*

@Entity
class Certification(

    @Column(nullable = false)
    var code: String,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
    private var expiredTime: Long,

    @Column(nullable = false)
    var isCertified: Boolean = false,

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "certification_id", nullable = false)
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