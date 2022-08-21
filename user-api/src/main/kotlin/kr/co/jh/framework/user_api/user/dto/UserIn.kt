package kr.co.jh.framework.user_api.user.dto

import kr.co.jh.framework.entity.user.domain.User
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
class UserIn(val userId: String, val email: String) {

    companion object {
        fun toEntity(userIn: UserIn) : User {
            return User(userId = userIn.userId, email = userIn.email)
        }

    }

}
