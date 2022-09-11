package kr.co.jh.framework.entity.user.repository

import kr.co.jh.framework.entity.exception.DomainEntityNotFoundException
import kr.co.jh.framework.entity.user.domain.QUser
import kr.co.jh.framework.entity.user.domain.User
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport

class UserRepositoryImpl :QuerydslRepositorySupport(User::class.java), UserRepositoryCustom {
    val qUser: QUser = QUser.user

    override fun getByPK(id: Long) : User {
        return from(qUser)
            .where(qUser.id.eq(id))
            .fetchOne() ?: throw DomainEntityNotFoundException(id, "찾는 유저가 존재하지않습니다.")
    }

    override fun getByUserId(userId: String): User {
        return from(qUser)
            .where(qUser.userId.eq(userId))
            .fetchOne() ?: throw DomainEntityNotFoundException(userId, "찾는 유저가 존재하지않습니다.")
    }


}
