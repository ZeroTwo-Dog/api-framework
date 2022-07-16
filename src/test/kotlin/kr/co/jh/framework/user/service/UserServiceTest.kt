package kr.co.jh.framework.user.service

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester


@SpringBootTest
@AutoConfigureGraphQlTester
class UserServiceTest (
    @Autowired private val userRepository: UserRepository,
    @Autowired private val graphQlTester:GraphQlTester
    ) {


    @Test
    @DisplayName("유저 단건 조회")
    fun graphQLFindById () {
        val user1 = User("wl507","wl5407@gmail.com");
        val user2 = User("283po1","283po1@naver.com");
        val user3 = User("wls507","wls5047@naver.com");

        val list = mutableListOf(user1, user2, user3)


        userRepository.saveAll(list)

        val email = graphQlTester.documentName("userFindById")
            .variable("id", 1L)
            .execute()
            .path("graphQLFindById.email")
            .entity(String::class.java)
            .get()


        assertEquals(email,"wl5407@gmail.com")

    }


    @Test
    @DisplayName("유저 다건 조회")
    fun getList () {
        val user1 = User("wl507","wl5407@gmail.com");
        val user2 = User("283po1","283po1@naver.com");
        val user3 = User("wls507","wls5047@naver.com");

        val list = mutableListOf(user1, user2, user3)


        userRepository.saveAll(list)

        graphQlTester.documentName("userGetList")
            .execute()
            .path("getList[*].userId")
            .entityList(String::class.java)
            .contains("283po1")



    }


    @Test
    @DisplayName("유저 저장")
    fun saveUser () {

        val user: MutableMap<String, Any> = HashMap()
        user["userId"] = "wl507"
        user["email"] = "wl5407@gmail.com"


        val userId = graphQlTester.documentName("saveUser")
            .variable("input", user)
            .execute()
            .path("saveUser.userId")
            .entity(String::class.java)
            .get()

        assertEquals(userId,"wl507")


    }
}
