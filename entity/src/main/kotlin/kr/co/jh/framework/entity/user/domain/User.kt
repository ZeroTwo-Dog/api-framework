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

    @Column(name = "PASSWORD")
    val password: String,


    @Column(name = "USER_ID")
    val userId: String,

    @Column(name = "EMAIL")
    val email: String,


    @Column(name = "FAIL_CNT")
    var failCnt: Int = 0,// 로그인 실패횟수


    @Column(name = "LOCK_YN")
    val locked: Boolean = false, // 계정

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    val status: Status = Status.ACTIVE, // 계정




// Set을 관계를 표현하는건 ROLE은 중복이 나면 안되기때문에 중복제한으로 사용
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
    name = "USER_ROLES",
    joinColumns = [JoinColumn(name = "USER_NO")],
    inverseJoinColumns = [JoinColumn(name = "ROLE_NO")])
    val roles: Set<Role> = HashSet()

    ) {

    constructor(userId: String, email: String, password: String) : this(null, password,userId,email)


    fun checkActiveUser(): Boolean {
        return status == Status.ACTIVE
    }

    fun checkWithdrawUser(): Boolean {
        return status == Status.WITHDRAW
    }

}

enum class Status() {
    ACTIVE,INACTIVE,WITHDRAW
}
