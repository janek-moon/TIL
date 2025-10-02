package study.janek.simplemultimoduleapp.domain.user

interface UserRepository {

    fun getAllUsers(): List<User>

    fun createUser(user: User): User

}