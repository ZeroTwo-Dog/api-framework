package kr.co.jh.framework.lib.security

import kr.co.jh.framework.entity.user.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

/**
 * Created by park on
 * 2022/09/04.
 */
@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {


    @Transactional(readOnly = true)
    override fun loadUserByUsername(userId: String): UserDetails {
        val user = userRepository.getByUserId(userId)

        return UserPrincipal.fromEntity(user)
    }
}
