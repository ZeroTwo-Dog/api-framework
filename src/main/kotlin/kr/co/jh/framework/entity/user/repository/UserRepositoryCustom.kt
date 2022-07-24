package kr.co.jh.framework.entity.user.repository

import kr.co.jh.framework.entity.user.domain.User

interface UserRepositoryCustom {

    fun getByPK(no: Long) : User
}
