package com.nordpass.tt.storage.todo

import com.nordpass.tt.usecase.Todo
import org.threeten.bp.OffsetDateTime
import org.threeten.bp.format.DateTimeFormatter
import javax.inject.Inject

internal class TodoMapper @Inject constructor() {

    private val dateFormatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    fun map(entity: TodoEntity): Todo {
        return Todo(
            id = entity.id,
            title = entity.title,
            isCompleted = entity.isCompleted,
            updatedAt = if (entity.updatedAt.isNotEmpty()) OffsetDateTime.parse(entity.updatedAt) else null,
            dueOn = OffsetDateTime.parse(entity.dueOn)
        )
    }

    fun map(todo: Todo): TodoEntity {
        return TodoEntity(
            id = todo.id,
            title = todo.title,
            isCompleted = todo.isCompleted,
            updatedAt = todo.updatedAt?.let(dateFormatter::format) ?: "",
            dueOn = dateFormatter.format(todo.dueOn)
        )
    }
}