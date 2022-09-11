package kr.co.jh.framework.user_api.user.dto

import kr.co.jh.framework.entity.user.domain.User
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
class SignUpIn(val userId: String,
               val email: String,
               val password: String,
               ) {

    companion object {
        fun toEntity(signUpIn: SignUpIn, encodePassword: String) : User {
            return User(userId = signUpIn.userId, email = signUpIn.email,password = encodePassword)
        }

    }

}
