package study.janek.simplemultimoduleapp.infrastructure

import jakarta.persistence.MappedSuperclass
import java.util.UUID
import org.springframework.data.domain.Persistable
import jakarta.persistence.Column
import jakarta.persistence.Id
import jakarta.persistence.PostLoad
import jakarta.persistence.PostPersist
import org.hibernate.proxy.HibernateProxy
import java.util.Objects

@MappedSuperclass
abstract class BaseEntity(
    id: String = UUID.randomUUID().toString(),
) : Persistable<String> {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private val id: String = id

    @Transient
    private var isNew: Boolean = true

    override fun getId(): String {
        return id
    }

    override fun isNew(): Boolean {
        return true
    }

    @PostLoad
    @PostPersist
    protected fun onLoad() {
        isNew = false
    }

    override fun equals(other: Any?): Boolean {
        if (other == null) {
            return false
        }

        if (other !is HibernateProxy && this::class != other::class) {
            return false
        }

        return id == getIdentifier(other)
    }

    private fun getIdentifier(obj: Any): Any? {
        return if (obj is HibernateProxy) {
            obj.hibernateLazyInitializer.identifier
        } else {
            (obj as BaseEntity).id
        }
    }

    override fun hashCode() = Objects.hashCode(id)

}