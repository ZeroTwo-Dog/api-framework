package kr.co.jh.framework.user_api.user.service.command

import kr.co.jh.framework.entity.role.domain.Role
import kr.co.jh.framework.entity.role.domain.RoleType
import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.lib.security.UserPrincipal
import kr.co.jh.framework.lib.security.jwt.JwtAuthenticationResponse
import kr.co.jh.framework.lib.security.jwt.JwtTokenProvider
import kr.co.jh.framework.user_api.user.dto.LoginDto
import kr.co.jh.framework.user_api.user.dto.SignUpIn
import kr.co.jh.framework.user_api.user.exception.*
import kr.co.jh.framework.user_api.user.service.query.UserQueryService
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAuthCommandService (private val authenticationManager: AuthenticationManager,
                              private val jwtTokenProvider: JwtTokenProvider,
                              private val userRepository: UserRepository,
                              private val passwordEncoder: PasswordEncoder,
                              private val userQueryService: UserQueryService,
                              @Value("\${maxFailCnt}")
                              private val maxFailCnt: Int
 ) {



    /**
     * 로그인
     *
     * @param loginDto
     */
    fun login (loginDto: LoginDto): JwtAuthenticationResponse {
        val userPrincipal: UserPrincipal
        val authentication: Authentication = try {
            authenticationManager.authenticate(UsernamePasswordAuthenticationToken
                (loginDto.userId, loginDto.password))
        } catch (e: InternalAuthenticationServiceException) { // 존재하지 않는 사용자
            throw UserNotFoundException("등록된 회원이 존재하지 않습니다.")
        } catch (e: DisabledException) {  // 유효한 회원이 아님
            throw InvalidUserException("탈퇴한 회원입니다.")
        } catch (e: BadCredentialsException) {
            failPassword(loginDto.userId)
            throw BadCredentialsException("비밀번호를 틀렸습니다.")
        } catch (e: LockedException) {    // 계정 잠김
            throw UserLockException("회원 상태가 잠금입니다.")
        } catch (e: UnauthenticatedAccessException) {
            throw UnauthenticatedAccessException()
        }

        userPrincipal =
            authentication.principal as UserPrincipal
        SecurityContextHolder.getContext().authentication = authentication

        val jwt = jwtTokenProvider.generateToken(authentication)

        return JwtAuthenticationResponse(jwt, userPrincipal.getUserId(), userPrincipal.getRoles())
    }


    /**
    * 회원가입
    *
    * @param signUpIn
    */
    fun registerUser(signUpIn: SignUpIn) : User {
        userQueryService.checkUserId(signUpIn.userId)
        userQueryService.checkEmail(signUpIn.email)
        val user = SignUpIn.toEntity(signUpIn, passwordEncoder.encode(signUpIn.password))
        user.addRoles(Role(RoleType.ROLE_USER.value, RoleType.ROLE_USER))

        return userRepository.save(user)
    }


    fun failPassword(userId: String) {
        val user = userRepository.getByUserId(userId)
        user.checkFailCnt(maxFailCnt)
        userRepository.save(user)


    }






}

