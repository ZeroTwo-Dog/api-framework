package kr.co.jh.framework.user_api.user.service.command

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.lib.security.UserPrincipal
import kr.co.jh.framework.user_api.user.dto.LoginDto
import org.springframework.security.authentication.*
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Service

@Service
class UserAuthCommandService (private val userRepository: UserRepository,
                              private val authenticationManager: AuthenticationManager
 ) {

//    fun login (loginDto: LoginDto): User {
//        var userPrincipal: UserPrincipal
//        var authentication: Authentication = try {
//            authenticationManager.authenticate(loginDto.userId, loginDto.password)
//        } catch (e: InternalAuthenticationServiceException) { // 존재하지 않는 사용자
//            throw UserNotFoundException(("USER_NOT_FOUND"))
//        } catch (e: DisabledException) {  // 유효한 회원이 아님
//            throw InvalidUserException(MessageUtil.getMessage("LOGIN_FAIL"))
//        } catch (e: BadCredentialsException) {
//            failPassword(userId)
//            throw BadCredentialsException(MessageUtil.getMessage("FAIL_PASSWORD"))
//        } catch (e: LockedException) {    // 계정 잠김
//            throw UserLockException(
//                MessageUtil.getMessage("REQUIRED_CAPTCHA")
//            )
//        } catch (e: UnauthenticatedAccessException) {
//            throw UnauthenticatedAccessException()
//        }
//    }



}
