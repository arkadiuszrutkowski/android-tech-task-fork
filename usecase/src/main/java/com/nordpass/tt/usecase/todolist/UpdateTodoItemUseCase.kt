package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Completable
import javax.inject.Inject

class UpdateTodoItemUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun update(item: Todo): Completable {
        return storage.save(listOf(item))
    }
}