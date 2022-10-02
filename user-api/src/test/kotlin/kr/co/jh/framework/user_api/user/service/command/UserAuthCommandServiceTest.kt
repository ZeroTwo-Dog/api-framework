package kr.co.jh.framework.user_api.user.service.command

import kr.co.jh.framework.user_api.user.dto.LoginDto
import kr.co.jh.framework.user_api.user.exception.UserNotFoundException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("develop")
internal class UserAuthCommandServiceTest(
    @Autowired
    private val userAuthCommandService: UserAuthCommandService) {





    @Test
    @DisplayName("로그인 성공 케이스")
    fun login() {
        val userId = "wl5407"
        val password = "jh1234!@"
        val loginDto = LoginDto(userId, password)
        val loginUser = userAuthCommandService.login(loginDto)
        assertEquals(userId, loginUser.userId)

    }



    @Test
    @DisplayName("비밀번호 틀리는 케이스")
    fun loginPasswordFail() {
        val userId = "wl5407"
        val password = "jh1234"
        val loginDto = LoginDto(userId, password)
        assertThrows(BadCredentialsException::class.java) { userAuthCommandService.login(loginDto) }

    }


    @Test
    @DisplayName("데이터가 없는 케이스")
    fun userNotFound() {
        val userId = "wl54077"
        val password = "jh1234"
        val loginDto = LoginDto(userId, password)
        assertThrows(UserNotFoundException::class.java) { userAuthCommandService.login(loginDto) }

    }



}
