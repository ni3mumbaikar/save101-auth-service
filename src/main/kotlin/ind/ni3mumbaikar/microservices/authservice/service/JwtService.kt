package ind.ni3mumbaikar.microservices.authservice.service

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


@Component
class JwtService {
    val secretKey: String = System.getenv("SECRET_KEY") ?: "KEY_NOT_FOUND"

    private var logger: Logger = LoggerFactory.getLogger(JwtService::class.java)

    fun validateToken(token: String) {
        Jwts.parserBuilder().setSigningKey(getSigningKey()).build().parseClaimsJws(token)
        logger.debug("performed token validation for $token")
    }

    fun getSigningKey(): Key {
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(userName: String): String {
        val claims: Map<String, Any> = HashMap<String, Any>()
        return createToken(claims, userName)
    }

    fun createToken(claims: Map<String, Any>, userName: String): String {
        return Jwts.builder()
            .setClaims(claims)
            .setSubject(userName)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 30))
            .signWith(getSigningKey(), SignatureAlgorithm.HS256).compact()
    }

}