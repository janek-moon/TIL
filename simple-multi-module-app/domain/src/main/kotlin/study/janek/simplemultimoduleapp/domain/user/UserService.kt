package study.janek.simplemultimoduleapp.domain.user

interface UserService {

    fun getAllUsers(): List<User>

    fun createUser(user: User): User

}