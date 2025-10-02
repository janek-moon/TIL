package study.janek.simplemultimoduleapp.infrastructure.user

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table
import study.janek.simplemultimoduleapp.domain.user.User
import study.janek.simplemultimoduleapp.infrastructure.BaseEntity
import java.util.UUID

@Entity
@Table(name = "users")
class UserEntity(
    id: String = UUID.randomUUID().toString(),
    username: String,
    name: String,
    age: Int,
) : BaseEntity(id) {

    @Column(name = "username")
    val username: String = username

    @Column(name = "name")
    val name: String = name

    @Column(name = "age")
    val age: Int = age

    companion object {
        fun from(user: User): UserEntity {
            return UserEntity(
                username = user.username,
                name = user.name,
                age = user.age
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            username = username,
            name = name,
            age = age
        )
    }

}