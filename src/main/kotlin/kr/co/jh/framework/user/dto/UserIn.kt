package kr.co.jh.framework.user.dto

import kr.co.jh.framework.entity.user.domain.User
import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
class UserIn(val userId: String, val email: String) {

}
