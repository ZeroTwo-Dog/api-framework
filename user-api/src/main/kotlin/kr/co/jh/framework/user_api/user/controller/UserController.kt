package kr.co.jh.framework.user_api.user.controller

import kr.co.jh.framework.user_api.user.dto.UserIn
import kr.co.jh.framework.user_api.user.service.command.UserCommandService
import kr.co.jh.framework.user_api.user.service.query.UserQueryService
import kr.co.jh.framework.entity.user.domain.User
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@Controller
class UserController (private val userCommandService: UserCommandService, val userQueryService: UserQueryService){

    @GetMapping("/user/{id}")
    fun findById(@PathVariable("id") id: Long) : ResponseEntity<User> {
        return ResponseEntity.ok(userCommandService.findById(id))
    }

    @QueryMapping
    fun graphQLFindById(@Argument id: Long) : User {
        return userCommandService.findById(id)
    }

    @QueryMapping
    fun getList() : List<User> {
        return userCommandService.findByList()
    }

    @MutationMapping
    fun saveUser(@Argument input: UserIn) : User {
        return userQueryService.saveUser(input)
    }
}
