package kr.co.jh.framework.user_api.user.exception

import kr.co.jh.framework.lib.exception.exceptionClazz.CustomException

/**
 * Created by park on
 * 2022/09/11.
 */
class UserNotFoundException(
    @JvmField
    @Suppress("INAPPLICABLE_JVM_FIELD")
    override var message: String? = "등록된 회원이 존재하지 않습니다.",
) : CustomException(
    message
){
    override fun getMessage(): String? {
        return super.getMessage()
    }
}
