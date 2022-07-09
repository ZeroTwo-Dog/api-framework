package kr.co.jh.framework.entity.user.repository

import kr.co.jh.framework.entity.user.domain.User
import lombok.AllArgsConstructor
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.graphql.data.GraphQlRepository
import org.springframework.stereotype.Repository

//@Repository
@GraphQlRepository
interface UserRepository : JpaRepository<User, Long> {

}
