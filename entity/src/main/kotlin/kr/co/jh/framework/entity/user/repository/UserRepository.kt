package kr.co.jh.framework.entity.user.repository

import kr.co.jh.framework.entity.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.graphql.data.GraphQlRepository

@GraphQlRepository
interface UserRepository : JpaRepository<User, Long> , UserRepositoryCustom {

}
