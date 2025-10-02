package study.janek.simplemultimoduleapp.domain.user.impl

import org.springframework.stereotype.Service
import study.janek.simplemultimoduleapp.domain.user.User
import study.janek.simplemultimoduleapp.domain.user.UserRepository
import study.janek.simplemultimoduleapp.domain.user.UserService

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
) : UserService {
    override fun getAllUsers(): List<User> {
        return userRepository.getAllUsers()
    }

    override fun createUser(user: User): User {
        return userRepository.createUser(user)
    }

}