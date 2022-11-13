package kr.co.jh.framework.lib.security.interceptor

import graphql.GraphQLError
import graphql.GraphqlErrorBuilder
import kr.co.jh.framework.lib.utils.LoggerUtils
import kr.co.jh.framework.lib.utils.LoggerUtils.logger
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.server.WebGraphQlInterceptor
import org.springframework.graphql.server.WebGraphQlRequest
import org.springframework.graphql.server.WebGraphQlResponse
import reactor.core.publisher.Mono
import java.util.stream.Collectors


/**
 * Created by park on
 * 2022/11/13.
 */
@Configuration
class RequestErrorInterceptor: WebGraphQlInterceptor {
    override fun intercept(
        request: WebGraphQlRequest,
        chain: WebGraphQlInterceptor.Chain
    ): Mono<WebGraphQlResponse> {
        return chain.next(request).map { response ->
            if (response.isValid) return@map response
            val errors: List<GraphQLError> = response.errors.stream()
                .map { error ->
                    LoggerUtils.logger().error("RequestErrorInterceptor: "+error.message);
                    val builder = GraphqlErrorBuilder.newError()
                    return@map builder.build()
                }
                .collect(Collectors.toList())
            response.transform { builder -> builder.errors(errors).build() }
        }

    }
}
