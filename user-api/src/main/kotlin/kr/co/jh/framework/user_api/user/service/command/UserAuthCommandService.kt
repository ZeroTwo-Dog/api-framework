package kr.co.jh.framework.user_api.user.service.command

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.lib.security.UserPrincipal
import kr.co.jh.framework.user_api.user.dto.LoginDto
import kr.co.jh.framework.user_api.user.exception.InvalidUserException
import kr.co.jh.framework.user_api.user.exception.UnauthenticatedAccessException
import kr.co.jh.framework.user_api.user.exception.UserLockException
import kr.co.jh.framework.user_api.user.exception.UserNotFoundException
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class UserAuthCommandService (private val authenticationManager: AuthenticationManager
 ) {

    fun login (loginDto: LoginDto): User {
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

        return userPrincipal.getUser()
    }



}
