package com.nordpass.task.ui.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoDetailsViewModel @ViewModelInject constructor(
    private val updateTodoItemUseCase: UpdateTodoItemUseCase
): BaseViewModel() {

    val item = MutableLiveData<Todo>()

    fun init(item: Todo) {
        this.item.value = item
    }

    fun onFinishedClicked() {
        val finishedItem = item.value?.copy(isCompleted = true) ?: return
        updateTodoItemUseCase.update(finishedItem)
            .subscribeBy(onComplete = { item.postValue(finishedItem) }, onError = ::handleError)
            .attach()
    }

    fun onTodoClicked() {
        val unfinishedItem = item.value?.copy(isCompleted = false) ?: return
        updateTodoItemUseCase.update(unfinishedItem)
            .subscribeBy(onComplete = { item.postValue(unfinishedItem) }, onError = ::handleError)
            .attach()
    }

    fun onEditClicked() {
        //todo
    }
}