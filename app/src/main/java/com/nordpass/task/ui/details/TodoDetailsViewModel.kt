package com.nordpass.task.ui.details

import androidx.hilt.Assisted
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.SavedStateHandle
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.TimeProvider
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoDetailsViewModel @ViewModelInject constructor(
    @Assisted savedStateHandle: SavedStateHandle,
    private val timeProvider: TimeProvider,
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
) : BaseViewModel() {

    val item = savedStateHandle.getLiveData<Todo>("item")

    val eventStream = SingleLiveEvent<Event>()

    fun init(item: Todo) {
        this.item.value = item
    }

    fun onFinishedClicked() {
        updateCompletionState(true)
    }

    fun onTodoClicked() {
        updateCompletionState(false)
    }

    fun onEditClicked() {
        item.value?.let { todo ->
            eventStream.value = Event.GoToUpdateTodoDetails(todo)
        }
    }

    private fun updateCompletionState(isCompleted: Boolean) {
        val updatedItem = item.value?.copy(
            isCompleted = isCompleted,
            updatedAt = timeProvider.now()
        ) ?: return

        updateTodoItemUseCase.update(updatedItem)
            .subscribeBy(onComplete = { item.postValue(updatedItem) }, onError = ::handleError)
            .attach()
    }

    sealed class Event {
        data class GoToUpdateTodoDetails(val todo: Todo) : Event()
    }
}