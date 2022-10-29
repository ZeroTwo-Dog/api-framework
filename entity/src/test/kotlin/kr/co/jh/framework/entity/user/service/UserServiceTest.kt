package kr.co.jh.framework.entity.user.service

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.graphql.test.tester.GraphQlTester
import org.springframework.test.context.ActiveProfiles

@SpringBootTest(properties = ["spring.config.location=classpath:application-entity.yml"])
@ActiveProfiles("develop")
@AutoConfigureGraphQlTester
class UserServiceTest (
    @Autowired private val graphQlTester:GraphQlTester
    ) {


    @Test
    @DisplayName("유저 단건 조회")
    fun getUserFindById () {
        val email = graphQlTester.documentName("userFindById")
            .variable("id", 1L)
            .execute()
            .path("getUserFindById.email")
            .entity(String::class.java)
            .get()


        assertEquals(email,"wl5407@gmail.com")

    }


    @Test
    @DisplayName("유저 다건 조회")
    fun getUserList () {
        graphQlTester.documentName("usergetUserList")
            .execute()
            .path("getUserList[*].userId")
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
