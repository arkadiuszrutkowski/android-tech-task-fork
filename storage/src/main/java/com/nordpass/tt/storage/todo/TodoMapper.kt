package com.nordpass.tt.storage.todo

import com.nordpass.tt.usecase.Todo
import javax.inject.Inject

internal class TodoMapper @Inject constructor() {

    fun map(entity: TodoEntity): Todo {
        return Todo(
            id = entity.id,
            title = entity.title,
            isCompleted = entity.isCompleted,
            updatedAt = entity.updatedAt
        )
    }

    fun map(todo: Todo): TodoEntity {
        return TodoEntity(
            id = todo.id,
            title = todo.title,
            isCompleted = todo.isCompleted,
            updatedAt = todo.updatedAt
        )
    }
}