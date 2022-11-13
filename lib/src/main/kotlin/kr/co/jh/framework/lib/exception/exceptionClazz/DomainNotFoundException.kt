package kr.co.jh.framework.lib.exception.exceptionClazz

import graphql.ErrorClassification
import kr.co.jh.framework.lib.exception.ErrorType
import kr.co.jh.framework.lib.exception.GraphqlException

/**
 * Created by park on
 * 2022/11/13.
 */
open class DomainNotFoundException (
    @JvmField
    @Suppress("INAPPLICABLE_JVM_FIELD")
    override val message: String? = "Domain not founded",
) : GraphqlException(message) {

    override fun getMessage(): String? {
        return super.getMessage()
    }

    override fun getErrorType(): ErrorClassification {
        return ErrorType.DOMAIN_NOT_FOUND
    }
}
