package kr.co.jh.framework.user_api.user.service.query

import kr.co.jh.framework.user_api.user.dto.SignUpIn
import kr.co.jh.framework.user_api.user.exception.UserDuplicateException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Order

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles

@SpringBootTest
@ActiveProfiles("develop")
internal class UserAuthQueryServiceTest (
    @Autowired
    private val userAuthQueryService: UserAuthQueryService
        ){

    @Test
    @Order(1)
    @DisplayName("회원가입 성공 케이스")
    fun registerUser() {
        val signIn = SignUpIn("ghks5407", "ghks5407@naver.com","jh1234!@")
        val registerUser = userAuthQueryService.registerUser(signIn)

        assertEquals("ghks5407@naver.com",registerUser.email )


    }


    @Test
    @Order(2)
    @DisplayName("아이디 중복")
    fun checkId() {
        val signIn = SignUpIn("ghks5407", "ghks5411@naver.com","jh1234!@")
        assertThrows(UserDuplicateException::class.java) { userAuthQueryService.registerUser(signIn) }

    }


    @Test
    @Order(3)
    @DisplayName("이메일 중복")
    fun checkEmail() {
        val signIn = SignUpIn("ghks5412", "ghks5407@naver.com","jh1234!@")
        assertThrows(UserDuplicateException::class.java) { userAuthQueryService.registerUser(signIn) }

    }
}
