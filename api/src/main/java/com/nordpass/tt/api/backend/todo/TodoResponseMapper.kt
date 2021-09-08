package com.nordpass.tt.api.backend.todo

import com.nordpass.tt.usecase.Todo
import javax.inject.Inject

internal class TodoResponseMapper @Inject constructor() {

    fun map(response: TodoResponse): Todo? {
        val id = response.id ?: return null
        val title = response.title ?: return null
        val completed = response.status == "completed"
        return Todo(
            id = id,
            title = title,
            isCompleted = completed,
            updatedAt = ""
        )
    }
}