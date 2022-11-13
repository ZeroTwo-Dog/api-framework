package kr.co.jh.framework.lib.exception

import graphql.GraphQLError
import graphql.schema.DataFetchingEnvironment
import kr.co.jh.framework.entity.exception.DomainEntityNotFoundException
import kr.co.jh.framework.lib.exception.exceptionClazz.CustomException
import kr.co.jh.framework.lib.exception.exceptionClazz.DomainNotFoundException
import kr.co.jh.framework.lib.exception.exceptionClazz.UnauthorizedException
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter
import org.springframework.security.core.AuthenticationException

/**
 * Created by park on
 * 2022/11/13.
 */
@Configuration
class GraphqlExceptionConfig: DataFetcherExceptionResolverAdapter() {

    override fun resolveToSingleError(exception: Throwable, env: DataFetchingEnvironment): GraphQLError? {
        return when (exception) {

            //스프링에서 일반적으로 제공해주는 익셉션도 이렇게 다 세팅해주는게 맞는지 의문
            // Ex) AuthenticationException 이런 익센션...
            is CustomException -> CustomException(exception.message)
            is DomainEntityNotFoundException -> DomainNotFoundException(exception.message)
            is AuthenticationException -> UnauthorizedException(exception.message)
            else ->  super.resolveToSingleError(exception, env)
        }
    }
}
