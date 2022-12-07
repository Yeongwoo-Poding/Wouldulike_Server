package trying.cosmos.global.authentication

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.stereotype.Component

@Component
class TokenProvider(

    config: JwtConfiguration

) {

    val key = Keys.hmacShaKeyFor(config.key.toByteArray())

    fun createAccessToken(sessionId: String): String {
        return Jwts.builder()
            .setSubject(sessionId)
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