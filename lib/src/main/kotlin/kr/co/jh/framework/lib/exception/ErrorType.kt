package kr.co.jh.framework.lib.exception

import graphql.ErrorClassification

/**
 * Created by park on
 * 2022/11/13.
 */

//
enum class ErrorType : ErrorClassification {
    CUSTOM,
    UNAUTHORIZED,
    DOMAIN_NOT_FOUND
}
