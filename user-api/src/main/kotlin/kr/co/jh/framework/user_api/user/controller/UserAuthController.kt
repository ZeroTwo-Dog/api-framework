package kr.co.jh.framework.user_api.user.controller

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.user_api.user.dto.LoginDto
import kr.co.jh.framework.user_api.user.dto.SignUpIn
import kr.co.jh.framework.user_api.user.service.command.UserAuthCommandService
import kr.co.jh.framework.user_api.user.service.query.UserAuthQueryService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.security.access.prepost.PreAuthorize
//import org.springframework.security.authentication.AuthenticationManager
import org.springframework.stereotype.Controller
import org.springframework.validation.Errors

/**
 * Created by park on
 * 2022/09/04.
 */
@Controller
class UserAuthController(
    private val userAuthCommandService: UserAuthCommandService,
    private val userAuthQueryService: UserAuthQueryService
){


    //회원가입
    @MutationMapping
    @PreAuthorize("isAnonymous()")
    fun registerUser(@Argument input: SignUpIn): User {
        return userAuthQueryService.registerUser(input)

    }


    @MutationMapping
    @PreAuthorize("isAnonymous()")
    fun login(@Argument input: LoginDto): User {
        return userAuthCommandService.login(input)

    }


}
