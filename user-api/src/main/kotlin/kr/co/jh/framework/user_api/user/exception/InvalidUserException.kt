package kr.co.jh.framework.user_api.user.exception

import java.lang.RuntimeException

/**
 * Created by park on
 * 2022/09/11.
 */
class InvalidUserException(
    message: String? = null
) : RuntimeException(
    message
)
