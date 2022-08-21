package kr.co.jh.framework.entity.exception

import java.lang.RuntimeException

class DomainEntityNotFoundException(
    val id: Any,
    msg: String) : RuntimeException(msg) {}
