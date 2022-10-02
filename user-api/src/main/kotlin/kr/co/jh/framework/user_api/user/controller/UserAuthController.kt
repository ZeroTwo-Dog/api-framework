package kr.co.jh.framework.user_api.user.controller

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.lib.security.jwt.JwtAuthenticationResponse
import kr.co.jh.framework.user_api.user.dto.LoginDto
import kr.co.jh.framework.user_api.user.dto.SignUpIn
import kr.co.jh.framework.user_api.user.service.command.UserAuthCommandService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.stereotype.Controller

/**
 * Created by park on
 * 2022/09/04.
 */
@Controller
class UserAuthController(
    private val userAuthCommandService: UserAuthCommandService,
    private val userAuthQueryService: UserAuthCommandService
){


    //회원가입
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    fun registerUser(@Argument input: SignUpIn): JwtAuthenticationResponse {
        val registerUser = userAuthQueryService.registerUser(input)

        //회원가입후 바로 로그인
        return userAuthQueryService.login(LoginDto(registerUser.userId,registerUser.password))

    }


    @MutationMapping
    @PreAuthorize("isAnonymous()")
    fun login(@Argument input: LoginDto): JwtAuthenticationResponse {
        return userAuthCommandService.login(input)

    }


}
