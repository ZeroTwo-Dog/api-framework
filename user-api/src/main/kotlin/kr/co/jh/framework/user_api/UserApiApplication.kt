package kr.co.jh.framework.user_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication(scanBasePackages = [
    "kr.co.jh.framework.entity","kr.co.jh.framework.user_api" ]
)
@EntityScan("kr.co.jh.framework.entity")
@EnableJpaRepositories("kr.co.jh.framework.entity")
class UserApiApplication

fun main(args: Array<String>) {
    runApplication<UserApiApplication>(*args)
}
