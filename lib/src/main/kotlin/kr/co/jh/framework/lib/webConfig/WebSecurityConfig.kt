package kr.co.jh.framework.lib.webConfig

import kr.co.jh.framework.lib.security.AuthenticationEntryPoint
import kr.co.jh.framework.lib.security.CustomAccessDeniedHandler
import kr.co.jh.framework.lib.security.jwt.JwtAuthenticationEntryPoint
import kr.co.jh.framework.lib.security.jwt.JwtAuthenticationFilter
import lombok.AllArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.BeanIds
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter


/**
 * Created by park on
 * 2022/08/28.
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
@AllArgsConstructor
class WebSecurityConfig(private val userDetailsService: UserDetailsService,
                        private val authenticationEntryPoint: JwtAuthenticationEntryPoint,
                        private val jwtAuthenticationFilter: JwtAuthenticationFilter,
                        private val accessDeniedHandler: CustomAccessDeniedHandler
) {

    @Bean
    fun passwordEncoder(): PasswordEncoder{
        return BCryptPasswordEncoder()
    }


    @Bean(BeanIds.AUTHENTICATION_MANAGER)
    @Throws(java.lang.Exception::class)
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.authenticationManager
    }

    @Bean
    fun filterChain(http :HttpSecurity) :SecurityFilterChain{

        return http.userDetailsService(userDetailsService)
            .csrf().disable()
            .formLogin().disable()
            //TODO: 시큐리티 작업끝나면 그래퓨큐엘 에러메시지 바꿔야함
            .addFilterBefore(jwtAuthenticationFilter, AnonymousAuthenticationFilter::class.java)
            .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .accessDeniedHandler(accessDeniedHandler)
            .and()
            .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and()
            .headers()
                .frameOptions()
                .sameOrigin()
            .and()
            .build()
    }


}
