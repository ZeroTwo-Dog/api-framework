package kr.co.jh.framework.user_api.user.service.query

import kr.co.jh.framework.user_api.user.dto.UserIn
import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserQueryService (val userRepository: UserRepository){
    fun saveUser(user: UserIn) : User {

        return userRepository.save(UserIn.toEntity(user))
    }

}
