package com.nordpass.tt.api.backend.todo

import com.nordpass.tt.usecase.Todo
import org.threeten.bp.OffsetDateTime
import javax.inject.Inject

internal class TodoResponseMapper @Inject constructor() {

    fun map(response: TodoResponse): Todo? {
        val id = response.id ?: return null
        val title = response.title ?: return null
        val completed = response.status == "completed"
        val dueOn = response.due_on ?: return null
        return Todo(
            id = id,
            title = title,
            isCompleted = completed,
            updatedAt = null,
            dueOn = OffsetDateTime.parse(dueOn)
        )
    }
}