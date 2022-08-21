package kr.co.jh.framework.user_api.user.service

import kr.co.jh.framework.entity.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest


@SpringBootTest
@AutoConfigureGraphQlTester
class UserQueryDslServiceTest (
    @Autowired private val userRepository: UserRepository
) {


    @Test
    @DisplayName("유저 단건 조회")
    fun graphQLFindById () {


        val user = userRepository.getByPK(1L)



        assertEquals(user.email,"wl5407@gmail.com")

    }


}
