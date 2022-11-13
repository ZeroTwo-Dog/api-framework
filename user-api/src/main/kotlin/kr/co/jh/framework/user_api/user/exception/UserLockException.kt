package kr.co.jh.framework.user_api.user.exception

import kr.co.jh.framework.lib.exception.exceptionClazz.CustomException

/**
 * Created by park on
 * 2022/09/11.
 */

class UserLockException(
    @JvmField
    @Suppress("INAPPLICABLE_JVM_FIELD")
    override var message: String? = "회원 상태가 잠금입니다.",
) : CustomException(
    message
){

    override fun getMessage(): String? {
        return super.getMessage()
    }
}
