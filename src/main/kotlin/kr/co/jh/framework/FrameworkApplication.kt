package kr.co.jh.framework

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FrameworkApplication

fun main(args: Array<String>) {
	runApplication<FrameworkApplication>(*args)
}
