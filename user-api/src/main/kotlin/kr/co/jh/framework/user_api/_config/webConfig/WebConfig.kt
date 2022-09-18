package kr.co.jh.framework.user_api._config.webConfig

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

/*
    WebConfig 에서는 커스텀 필터를 담당하는데
    커스텀 필터는 api 별로 다르기때문에 lib 가 아니라
    api 로 분리하였음
 */

/**
 * Created by park on
 * 2022-08-28
 */
@Configuration
@EnableWebMvc
class WebConfig : WebMvcConfigurer {

    @Bean
    fun restTemplate(): RestTemplate {
        return RestTemplate()
    }
}
