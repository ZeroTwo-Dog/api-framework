package kr.co.jh.framework.user.service

import kr.co.jh.framework.entity.user.repository.UserRepository
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest (
    @Autowired private val userRepository: UserRepository) {


    @Test
    fun readUser () {
        val user = userRepository.findById(1)

        Assertions.assertEquals(user.get().email, "wl5407@gmail.com")
    }
}
