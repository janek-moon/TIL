package org.example

object User: BaseTable("users") {
    val name = varchar("name", MAX_VARCHAR_LENGTH)
}