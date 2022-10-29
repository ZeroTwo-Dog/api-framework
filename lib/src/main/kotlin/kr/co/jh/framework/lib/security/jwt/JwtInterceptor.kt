package kr.co.jh.framework.lib.security.jwt

import kr.co.jh.framework.lib.security.CustomUserDetailsService
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.server.WebGraphQlInterceptor
import org.springframework.graphql.server.WebGraphQlRequest
import org.springframework.graphql.server.WebGraphQlResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.util.StringUtils
import reactor.core.publisher.Mono
import javax.servlet.http.HttpServletRequest

/**
 * Created by park on
 * 2022/10/22
 */
@Configuration
class JwtInterceptor(private val jwtTokenProvider: JwtTokenProvider,
                     private val customUserDetailsService: CustomUserDetailsService
) : WebGraphQlInterceptor {


    private val AUTHORIZATION_HEADER = "Authorization"
    private val AUTHORIZATION_BEARER = "Bearer "


    override fun intercept(
        request: WebGraphQlRequest,
        chain: WebGraphQlInterceptor.Chain
    ): Mono<WebGraphQlResponse> {

        if (isAnonymousAble(request.document)) {
            return chain.next(request);
        }

        val jwt: String = resolveToken(request)

        try {
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                val userId: String = jwtTokenProvider.getUserIdFromJWT(jwt)
                val userDetails: UserDetails =
                    customUserDetailsService.loadUserByUsername(userId)
                val authenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                val httpRequest =  request as HttpServletRequest
                authenticationToken.details = WebAuthenticationDetailsSource()
                    .buildDetails(httpRequest)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        } catch (e: RuntimeException) {
            SecurityContextHolder.clearContext()
        }

        return chain.next(request);
    }

    /**
     * Request Header에서 토큰 정보를 꺼내오기 위한 resolveToken 메서드
     */
    private fun resolveToken(request: WebGraphQlRequest): String {
        val bearerToken = request.headers[AUTHORIZATION_HEADER].toString()
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_BEARER)) {
            bearerToken.substring(7)
        } else ""
    }


    //권한이 필요없는 요청 체크
    private fun isAnonymousAble(document: String) : Boolean {
        //FIMXE: 권한 필요없는 요청 추가시 아래에 리스트 추가
        val isAnonymousList = listOf("login", "registerUser")
        for (list in isAnonymousList) {
            if (document.contains(list)) {
                return true
            }
        }
        return false
    }
}
