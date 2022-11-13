package kr.co.jh.framework.user_api.user.exception

import kr.co.jh.framework.lib.exception.exceptionClazz.CustomException

/**
 * Created by park on
 * 2022/09/11.
 */

class UserDuplicateException(
    @JvmField
    @Suppress("INAPPLICABLE_JVM_FIELD")
    override var message: String? = "중복된 회원입니다.",
) : CustomException(
    message
){
    override fun getMessage(): String? {
        return super.getMessage()
    }
}
