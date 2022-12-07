package trying.cosmos.global.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component
import trying.cosmos.domain.user.entity.Session

@Component
class TokenProvider(

    config: JwtConfiguration

) {

    private val AUTHORITY_HEADER = "auth"
    private val key = Keys.hmacShaKeyFor(config.key.toByteArray())

    fun createAccessToken(session: Session): String {
        return Jwts.builder()
            .setSubject(session.id)
            .claim(AUTHORITY_HEADER, session.authorityType)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }

    fun getSubject(token: String): String? {
        return parseClaims(token)?.subject
    }

    private fun parseClaims(token: String): Claims? {
        return Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(token).body
    }
}