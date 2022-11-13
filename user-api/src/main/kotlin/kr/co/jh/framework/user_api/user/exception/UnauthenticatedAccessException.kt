package kr.co.jh.framework.user_api.user.exception

import org.springframework.security.core.AuthenticationException

/**
 * Created by park on
 * 2022/09/11.
 */
class UnauthenticatedAccessException(
    message: String? = null
) : AuthenticationException(
    message
)
