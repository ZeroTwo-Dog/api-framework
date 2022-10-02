package kr.co.jh.framework.lib.security.jwt

import kr.co.jh.framework.lib.security.CustomUserDetailsService
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
/**
 * Created by park on
 * 2022/10/02.
 */
@Component
class JwtAuthenticationFilter(private val jwtTokenProvider: JwtTokenProvider,
    private val customUserDetailsService: CustomUserDetailsService) :GenericFilterBean() {

    private val AUTHORIZATION_HEADER = "Authorization"
    private val AUTHORIZATION_BEARER = "Bearer "


    override fun doFilter(
        request: ServletRequest,
        response: ServletResponse,
        chain: FilterChain
    ) {

        val jwt: String = resolveToken(request as HttpServletRequest)

        try {
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                val userId: String = jwtTokenProvider.getUserIdFromJWT(jwt)
                val userDetails: UserDetails =
                    customUserDetailsService.loadUserByUsername(userId)
                val authenticationToken =
                    UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
                authenticationToken.details = WebAuthenticationDetailsSource()
                    .buildDetails(request)
                SecurityContextHolder.getContext().authentication = authenticationToken
            }
        } catch (e: RuntimeException) {
            SecurityContextHolder.clearContext()
        }

        chain.doFilter(request, response)

    }


    /**
     * Request Header에서 토큰 정보를 꺼내오기 위한 resolveToken 메서드
     */
    private fun resolveToken(request: HttpServletRequest): String {
        val bearerToken = request.getHeader(AUTHORIZATION_HEADER)
        return if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(AUTHORIZATION_BEARER)) {
            bearerToken.substring(7)
        } else ""
    }
}




