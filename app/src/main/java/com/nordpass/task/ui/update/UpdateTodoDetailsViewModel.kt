package com.nordpass.task.ui.update

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.nordpass.task.R
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.ResourceProvider
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.rxkotlin.subscribeBy

class UpdateTodoDetailsViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val resourceProvider: ResourceProvider,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
) : BaseViewModel() {

    val todoText = savedStateHandle.getLiveData<String>("todoText")
    val textError = mediatorLiveData(todoText) {
        if (it.isNotEmpty()) "" else resourceProvider.getString(R.string.error_text_not_empty)
    }
    val saveEnabled = mediatorLiveData(todoText) { it.isNotEmpty() }

    val eventStream = SingleLiveEvent<Event>()

    private val todo = savedStateHandle.getLiveData<Todo>("todo")

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
                    eventStream.postValue(Event.TodoUpdateFinished(updatedTodo))
                },
                onError = ::handleError
            )
            .attach()
    }

    sealed class Event {
        data class TodoUpdateFinished(val updatedTodo: Todo) : Event()
    }
}