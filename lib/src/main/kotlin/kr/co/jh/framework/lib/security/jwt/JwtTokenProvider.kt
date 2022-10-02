package kr.co.jh.framework.lib.security.jwt

import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import kr.co.jh.framework.lib.security.UserPrincipal
import kr.co.jh.framework.lib.utils.LoggerUtils
import kr.co.jh.framework.lib.utils.LoggerUtils.logger
import org.springframework.beans.factory.InitializingBean
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.security.Key
import java.util.*


/**
 * JWT 토큰 생성 및 유효성 검증
 * Created by park on
 * 2022/10/02.
 */
@Component
class JwtTokenProvider(

    @Value("\${jwt.secret}")
    private val jwtSecret: String,

    @Value("\${jwt.expirationMills}")
    private val jwtExpirationMills: Int

) :InitializingBean {

    private val AUTHORITIES_KEY = "auth"

    private var key: Key? = null


    //토큰 생성
    fun generateToken(authentication: Authentication): String {
        val userPrincipal: UserPrincipal = authentication.principal as UserPrincipal
        val issuedAt = Date()
        val expiration = Date(issuedAt.time + jwtExpirationMills)

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .claim(AUTHORITIES_KEY, userPrincipal.authorities.iterator().next().toString())
            .setIssuedAt(issuedAt)
            .setExpiration(expiration)
            .signWith(key, SignatureAlgorithm.HS256)
            .compact()
    }


    fun getUserIdFromJWT(token: String): String {
        return Jwts.parserBuilder()
            .setSigningKey(jwtSecret)
            .build()
            .parseClaimsJws(token)
            .body.subject
    }

    //유효성 검증
    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder().setSigningKey(jwtSecret).build().parseClaimsJws(token) //NOPMD
            return true
        } catch (ex: SecurityException ) {
            LoggerUtils.logger().error("Invalid JWT signature")
        } catch (ex: MalformedJwtException) {
            LoggerUtils.logger().error("Invalid JWT token")
        } catch (ex: ExpiredJwtException) {
            LoggerUtils.logger().error("Expired JWT token")
        } catch (ex: UnsupportedJwtException) {
            LoggerUtils.logger().error("Unsupported JWT token")
        } catch (ex: IllegalArgumentException) {
            LoggerUtils.logger().error("JWT claims string is empty.")
        }
        return false
    }

    /*
    * 빈이 생성이 되고 의존성 주입까지 받은 후에, 주입된 secret 값을 Base 64
    *  Decode해서 key 변수에 할당하기 위함이다.
    * */
    override fun afterPropertiesSet() {
        val keyBytes = Decoders.BASE64.decode(jwtSecret)
        key = Keys.hmacShaKeyFor(keyBytes)
    }


}
