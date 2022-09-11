package kr.co.jh.framework.entity.user.repository

import kr.co.jh.framework.entity.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.graphql.data.GraphQlRepository
import java.util.*

@GraphQlRepository
interface UserRepository : JpaRepository<User, Long> , UserRepositoryCustom {


    fun findByUserId(userId: String): Optional<User>
    fun findByEmail(email: String): Optional<User>
}
