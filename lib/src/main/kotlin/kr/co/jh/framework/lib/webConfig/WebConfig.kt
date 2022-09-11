package kr.co.jh.framework.lib.webConfig

import org.springframework.context.annotation.Bean
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/**
 * Created by park on
 * 2022/09/11.
 */
class WebConfig : WebMvcConfigurer {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }

//    TODO: 인터셉터 테스트 구현 필요 (인터셉터 구현체에서 쿼리 받을수 있는지 확인필요)
//    override fun addInterceptors(registry: InterceptorRegistry) {
//        registry.addInterceptor(testInterceptor)
//    }
}
