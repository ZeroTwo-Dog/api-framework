package kr.co.jh.framework.user.service

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserCommandService (private val userRepository: UserRepository) {


    fun findById(id: Long): User {
        return userRepository.findById(id).get()
    }

    fun findByList(): List<User> {
        return userRepository.findAll()
    }

    fun save(user: User) :User {
        return userRepository.save(user)
    }

}
