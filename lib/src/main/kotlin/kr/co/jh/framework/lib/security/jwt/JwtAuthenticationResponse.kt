package kr.co.jh.framework.lib.security.jwt

import kr.co.jh.framework.entity.role.domain.Role
import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor

/**
 * Created by park on
 * 2022/10/02.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
class JwtAuthenticationResponse(val accessToken: String,
    val userId: String, val roles: Set<Role>){


}
