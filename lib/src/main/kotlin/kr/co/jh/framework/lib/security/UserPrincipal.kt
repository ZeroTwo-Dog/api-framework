package kr.co.jh.framework.lib.security

import com.fasterxml.jackson.annotation.JsonProperty
import kr.co.jh.framework.entity.role.domain.Role
import kr.co.jh.framework.entity.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.function.Function
import java.util.stream.Collectors

/**
 * Created by park on
 * 2022/09/04.
 */
class UserPrincipal (
    private val id: Long?,
    private val userId: String,

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private val password: String,

    private val accountNonLocked: Boolean,

    private val enabled: Boolean,

    private val authorities: Collection<GrantedAuthority>,

    private val roles: Set<Role>,

    private val user: User
) : UserDetails {
    override fun getAuthorities(

    ): Collection<GrantedAuthority> {
        return this.authorities
    }

    override fun getPassword(): String {
        return this.password
    }

    override fun getUsername(): String {
        return this.userId
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return accountNonLocked
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return this.enabled
    }


    fun getId(): Long? {
        return id
    }

    fun getUserId(): String {
        return userId
    }

    fun getRoles(): Set<Role> {
        return roles
    }

    fun getUser(): User {
        return user
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserPrincipal

        if (id != other.id) return false
        if (userId != other.userId) return false
        if (password != other.password) return false
        if (authorities != other.authorities) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + userId.hashCode()
        result = 31 * result + password.hashCode()
        result = 31 * result + authorities.hashCode()
        return result
    }


    companion object {
        fun fromEntity(user: User) : UserPrincipal {
            return UserPrincipal(
                user.id,
                user.userId,
                user.password,
                accountNonLocked = !user.locked,
                enabled = user.checkActiveUser(),
                authorities = user.roles.stream()
                    .map { role: Role ->
                        SimpleGrantedAuthority(
                            role.roleType.name
                        )
                    }
                    .collect(Collectors.toList()),
                roles = user.roles,
                user = user
            )
        }

    }



}
