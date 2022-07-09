package kr.co.jh.framework

import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
class InitDB (val initService: InitService) {

    @PostConstruct
    fun init () {
        initService.dbInit()
    }
}
