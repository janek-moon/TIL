package org.example

object Tasks : BaseTable("tasks") {
    val user = reference("user_id", User)
    val title = varchar("name", MAX_VARCHAR_LENGTH)
    val description = varchar("description", MAX_VARCHAR_LENGTH)
    val isCompleted = bool("completed").default(false)
}