package kr.co.jh.framework.lib.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.web.SecurityFilterChain

/**
 * Created by park on
 * 2022/08/28.
 */
@EnableWebSecurity
@Configuration
class WebSecurityConfig  {

    @Bean
    fun filterChain(http :HttpSecurity) :SecurityFilterChain{
        http.csrf().disable()

        //TODO: 로그인 회원가입 만들어지면 제거 (form 형식 사용)
        http.formLogin().disable()

        return http.build()

    }


}
