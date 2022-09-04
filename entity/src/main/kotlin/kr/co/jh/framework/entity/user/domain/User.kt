package kr.co.jh.framework.entity.user.domain

import kr.co.jh.framework.entity.role.domain.Role
import lombok.AllArgsConstructor
import lombok.EqualsAndHashCode
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name = "USER")
@EqualsAndHashCode
class User(
    @Id @GeneratedValue
    @Column(name = "USER_NO", nullable = false)
    val id: Long? = null,


    @Column(name = "USER_ID")
    val userId: String,

    @Column(name = "EMAIL")
    val email: String,


    // Set을 관계를 표현하는건 ROLE은 중복이 나면 안되기때문에 중복제한으로 사용
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "USER_ROLES",
    joinColumns = [JoinColumn(name = "USER_NO")],
    inverseJoinColumns = [JoinColumn(name = "ROLE_NO")])
    private val roles: Set<Role> = HashSet()

    ) {

    constructor(userId: String, email: String) : this(null, userId,email)

}
