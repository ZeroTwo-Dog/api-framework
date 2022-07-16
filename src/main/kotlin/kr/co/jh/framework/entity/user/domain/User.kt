package kr.co.jh.framework.entity.user.domain

import kr.co.jh.framework.user.dto.UserIn
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name = "USER")
@EqualsAndHashCode
class User(
    @Id @GeneratedValue
    @Column(name = "NO")
    val no: Long?,


    @Column(name = "USER_ID")
    val userId: String,

    @Column(name = "EMAIL")
    val email: String
        ){

    constructor(userId: String, email: String) : this(null, userId,email)

    companion object {
        fun toEntity(userIn: UserIn): User {
            return User(null, userIn.userId, userIn.email )

        }
    }
}
