package kr.co.jh.framework.user_api.user.exception

import java.lang.RuntimeException

/**
 * Created by park on
 * 2022/09/11.
 */
class UserNotFoundException(
    message: String? = null
) : RuntimeException(
    message
)
