package org.example

import org.jetbrains.exposed.v1.core.dao.id.UUIDTable
import org.jetbrains.exposed.v1.javatime.datetime
import java.time.LocalDateTime

const val MAX_VARCHAR_LENGTH = 128

abstract class BaseTable(
    tableName: String,
    createdAt: LocalDateTime = LocalDateTime.now(),
) : UUIDTable(tableName) {
    val createdAt = datetime("created_at").default(createdAt)
    val updatedAt = datetime("updated_at").nullable()
}