package kr.co.jh.framework.entity.role.repository

import kr.co.jh.framework.entity.role.domain.Role
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.graphql.data.GraphQlRepository

/**
 * Created by park on
 * 2022/09/04.
 */
@GraphQlRepository
interface RoleRepository: JpaRepository<Role, Long> {
}
