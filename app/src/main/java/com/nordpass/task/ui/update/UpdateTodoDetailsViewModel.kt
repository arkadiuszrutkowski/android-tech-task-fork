package com.nordpass.task.ui.update

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.ResourceProvider
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.rxkotlin.subscribeBy

class UpdateTodoDetailsViewModel @ViewModelInject constructor(
    private val resourceProvider: ResourceProvider,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
) : BaseViewModel() {

    val todoText = MutableLiveData<String>()
    val textError = mediatorLiveData(todoText) {
        if (it.isNotEmpty()) "" else resourceProvider.getString(R.string.error_text_not_empty)
    }
    val saveEnabled = mediatorLiveData(todoText) { it.isNotEmpty() }

    val navigationEvents = SingleLiveEvent<NavigationEvent>()

    private val todo = MutableLiveData<Todo>()

    fun init(todo: Todo) {
        this.todo.value = todo
        todoText.value = todo.title
    }

    fun onSaveClicked() {
        val updatedTodo = todo.value?.copy(
            title = todoText.value ?: "",
            updatedAt = Time.now()
        ) ?: return

        updateTodoItemUseCase.update(updatedTodo)
            .subscribeBy(
                onComplete = {
                    navigationEvents.postValue(NavigationEvent.TodoUpdateFinishedEvent(updatedTodo))
                },
                onError = ::handleError
            )
            .attach()
    }

    sealed class NavigationEvent {
        data class TodoUpdateFinishedEvent(val updatedTodo: Todo) : NavigationEvent()
    }
}