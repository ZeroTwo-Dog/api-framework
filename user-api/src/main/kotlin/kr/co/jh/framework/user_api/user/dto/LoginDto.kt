package kr.co.jh.framework.user_api.user.dto

import lombok.AllArgsConstructor
import lombok.Getter

@AllArgsConstructor
@Getter
class LoginDto(val userId: String, val password: String) {

}
