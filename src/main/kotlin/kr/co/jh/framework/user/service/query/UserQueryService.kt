package kr.co.jh.framework.user.service.query

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.user.dto.UserIn
import org.springframework.stereotype.Service

@Service
class UserQueryService (val userRepository: UserRepository){
    fun saveUser(user: UserIn) : User {

        return userRepository.save(User.toEntity(user))
    }

}
