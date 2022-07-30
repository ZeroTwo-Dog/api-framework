package kr.co.jh.framework.user.service.command

import kr.co.jh.framework.entity.exception.DomainEntityNotFoundException
import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.user.dto.UserIn
import org.springframework.stereotype.Service

@Service
class UserCommandService (private val userRepository: UserRepository) {


    fun findById(id: Long): User {
        return userRepository.findById(id).orElseThrow { DomainEntityNotFoundException(id, "유저가 존재하지 않습니다.") }
    }

    fun getByPK(id: Long): User {
        return userRepository.getByPK(id)
    }

    fun findByList(): List<User> {
        return userRepository.findAll()
    }

}
