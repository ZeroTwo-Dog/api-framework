package kr.co.jh.framework.user_api.user.service.query

import kr.co.jh.framework.entity.role.domain.Role
import kr.co.jh.framework.entity.role.domain.RoleType
import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.entity.user.repository.UserRepository
import kr.co.jh.framework.user_api.user.dto.SignUpIn
import kr.co.jh.framework.user_api.user.exception.UserDuplicateException
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserAuthQueryService (val userRepository: UserRepository,
                            val passwordEncoder: PasswordEncoder){
    fun registerUser(signUpIn: SignUpIn) : User {
        checkUserId(signUpIn.userId)
        checkEmail(signUpIn.email)
        val user = SignUpIn.toEntity(signUpIn, passwordEncoder.encode(signUpIn.password))
        user.addRoles(Role(RoleType.ROLE_USER.value, RoleType.ROLE_USER))

        return userRepository.save(user)
    }


    private fun checkUserId(userId: String) {
        if (userRepository.findByUserId(userId).isPresent)
            throw UserDuplicateException("USERID_ALREADY_REGISTERED")
    }

    private fun checkEmail(email: String) {
        if (userRepository.findByEmail(email).isPresent)
            throw UserDuplicateException("EMAIL_ALREADY_REGISTERED")
    }

}
