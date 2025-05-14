package study.janek.simplemultimoduleapp.web.user

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import study.janek.simplemultimoduleapp.domain.user.User
import java.util.UUID

@RestController
@RequestMapping("/api/v1/user")
class UserController {

    @GetMapping()
    fun getUser(): ResponseEntity<User> {
        return ResponseEntity.ok()
            .body(User(id = UUID.randomUUID().toString(), name = "Janek", username = "test@co.kr", age = 34))
    }

}