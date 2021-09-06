package com.nordpass.tt.usecase.todolist

import com.nordpass.tt.usecase.Todo
import io.reactivex.Single
import javax.inject.Inject

class GetTodoListUseCase @Inject constructor(
    private val storage: TodoStorage
) {
    fun get(): Single<List<Todo>> {
        return storage.getAll()
            .map { todoItems ->
                val unfinishedItems = todoItems.filter { it.isCompleted.not() }
                val finishedItems = todoItems.filter { it.isCompleted }
                sortTasksByDueOnDate(unfinishedItems) + sortTasksByDueOnDate(finishedItems)
            }
    }

    private fun sortTasksByDueOnDate(tasks: List<Todo>) =
        tasks.sortedBy { it.dueOn }
}