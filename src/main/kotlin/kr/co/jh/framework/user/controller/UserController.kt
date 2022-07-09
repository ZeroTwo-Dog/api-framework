package kr.co.jh.framework.user.controller

import kr.co.jh.framework.entity.user.domain.User
import kr.co.jh.framework.user.service.UserCommandService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserController (private val userService: UserCommandService){

    @GetMapping("/user/{id}")
    fun findById(@PathVariable("id") id: Long) : ResponseEntity<User> {
        return ResponseEntity.ok(userService.findById(id))
    }

    @QueryMapping
    fun graphQLFindById(@Argument id: Long) : User {
        return userService.findById(id)
    }

    @QueryMapping
    fun getList() : List<User> {
        return userService.findByList()
    }

    @MutationMapping
    fun save(@Argument user: User) : ResponseEntity<User> {
        return ResponseEntity.ok(userService.save(user))
    }
}
