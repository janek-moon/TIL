package study.janek.simplemultimoduleapp.web.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.janek.simplemultimoduleapp.domain.user.User
import study.janek.simplemultimoduleapp.domain.user.UserService

@RestController
@RequestMapping("/api/v1/user")
class UserController(
    private val userService: UserService,
) {

    @GetMapping
    fun getUser(): ResponseEntity<List<User>> {
        val result = userService.getAllUsers()

        return ResponseEntity.ok().body(result)
    }

    @PostMapping
    fun createUser(
        @RequestBody user: User
    ): ResponseEntity<User> {
        val result = userService.createUser(user)

        return ResponseEntity.ok().body(result)
    }

}