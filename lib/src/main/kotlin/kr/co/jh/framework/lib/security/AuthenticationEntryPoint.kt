package kr.co.jh.framework.lib.security

import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by park on
 * 2022/09/11.
 */
@Component
class AuthenticationEntryPoint : AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        response.sendError(
            HttpServletResponse.SC_UNAUTHORIZED,
            "UNAUTHORIZED_ACCESS"
        )
    }
}
