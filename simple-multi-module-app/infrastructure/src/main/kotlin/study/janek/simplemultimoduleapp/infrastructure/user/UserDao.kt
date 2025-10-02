package study.janek.simplemultimoduleapp.infrastructure.user

import jakarta.transaction.Transactional
import org.springframework.stereotype.Repository
import study.janek.simplemultimoduleapp.domain.user.User
import study.janek.simplemultimoduleapp.domain.user.UserRepository

@Repository
class UserDao(
    private val userJpaRepository: UserJpaRepository,
) : UserRepository {
    override fun getAllUsers(): List<User> {
        return userJpaRepository.findAll().map(UserEntity::toDomain)
    }

    @Transactional
    override fun createUser(user: User): User {
         return userJpaRepository.save(UserEntity.from(user)).toDomain()
    }
}