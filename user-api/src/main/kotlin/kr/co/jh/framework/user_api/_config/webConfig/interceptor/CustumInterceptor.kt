package kr.co.jh.framework.user_api._config.webConfig.interceptor

import kr.co.jh.framework.lib.utils.LoggerUtils
import kr.co.jh.framework.lib.utils.LoggerUtils.logger
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.server.WebGraphQlInterceptor
import org.springframework.graphql.server.WebGraphQlRequest
import org.springframework.graphql.server.WebGraphQlResponse
import reactor.core.publisher.Mono

/**
 * Created by park on
 * 2022/09/18.
 */
@Configuration
//TODO: 프로젝트에서 인터셉터 필요할때 아래 변경해서 사용
class CustumInterceptor : WebGraphQlInterceptor {


    override fun intercept(
        request: WebGraphQlRequest,
        chain: WebGraphQlInterceptor.Chain
    ): Mono<WebGraphQlResponse> {

        LoggerUtils.logger().error("request = {}", request);

        //document에 쿼리dsl 쿼리가 들어가 있으므로 거기서 contains로 메소드명으로 인터셉트 진행해야함
        LoggerUtils.logger().error("request getDocument = {}", request.document);
        LoggerUtils.logger().error("request getHeaders = {}", request.headers);

        return chain.next(request);
    }

}
