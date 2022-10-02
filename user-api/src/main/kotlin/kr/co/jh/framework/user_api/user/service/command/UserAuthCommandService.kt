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
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAuthCommandService (private val authenticationManager: AuthenticationManager,
                              private val jwtTokenProvider: JwtTokenProvider,
                              private val userRepository: UserRepository,
                              private val passwordEncoder: PasswordEncoder
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
            throw UserNotFoundException("USER_NOT_FOUND")
        } catch (e: DisabledException) {  // 유효한 회원이 아님
            throw InvalidUserException("LOGIN_FAIL")
        } catch (e: BadCredentialsException) {
//            TODO: lock Count 처리
//            failPassword(userId)
            throw BadCredentialsException("FAIL_PASSWORD")
        } catch (e: LockedException) {    // 계정 잠김
            throw UserLockException("REQUIRED_CAPTCHA")
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
        checkUserId(signUpIn.userId)
        checkEmail(signUpIn.email)
        val user = SignUpIn.toEntity(signUpIn, passwordEncoder.encode(signUpIn.password))
        user.addRoles(Role(RoleType.ROLE_USER.value, RoleType.ROLE_USER))

        return userRepository.save(user)
    }



    /**
     * 중복체크 아이디
     *
     * @param userId
     */
    private fun checkUserId(userId: String) {
        if (userRepository.findByUserId(userId).isPresent)
            throw UserDuplicateException("USERID_ALREADY_REGISTERED")
    }


    /**
     * 중복체크 이메일
     *
     * @param email
     */
    private fun checkEmail(email: String) {
        if (userRepository.findByEmail(email).isPresent)
            throw UserDuplicateException("EMAIL_ALREADY_REGISTERED")
    }




}

