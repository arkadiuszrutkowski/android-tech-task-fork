package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.Time
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoDetailsViewModel @ViewModelInject constructor(
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
) : BaseViewModel() {

    val item = MutableLiveData<Todo>()

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
        //todo
    }

    private fun updateCompletionState(isCompleted: Boolean) {
        val updatedItem = item.value?.copy(
            isCompleted = isCompleted,
            updatedAt = Time.now()
        ) ?: return

        updateTodoItemUseCase.update(updatedItem)
            .subscribeBy(onComplete = { item.postValue(updatedItem) }, onError = ::handleError)
            .attach()
    }
}