package kr.co.jh.framework.entity.role.domain

import lombok.AllArgsConstructor
import lombok.Getter
import lombok.NoArgsConstructor
import javax.persistence.*

/**
 * Created by park on
 * 2022/09/04.
 */
@AllArgsConstructor
@Getter
@NoArgsConstructor
@Entity(name = "ROLE")
class Role (
    @Id
    @Column(name = "ROLE_NO", nullable = false)
    var id: Long? = null,

    @Column(name = "ROLE_TYPE")
    @Enumerated(EnumType.STRING)
    val roleType: RoleType
){

}
