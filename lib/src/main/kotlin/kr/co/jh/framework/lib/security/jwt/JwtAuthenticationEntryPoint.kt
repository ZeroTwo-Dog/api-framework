package kr.co.jh.framework.lib.security.jwt

import kr.co.jh.framework.lib.utils.LoggerUtils
import kr.co.jh.framework.lib.utils.LoggerUtils.logger
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Created by park on
 * 2022/10/02.
 */
@Component
class JwtAuthenticationEntryPoint :AuthenticationEntryPoint {
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {

        LoggerUtils.logger().error("Unauthorized Error: {}", authException.message)
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "UNAUTHORIZED_ACCESS")
    }
}
