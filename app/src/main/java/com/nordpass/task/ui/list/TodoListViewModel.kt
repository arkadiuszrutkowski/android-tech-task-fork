package com.nordpass.task.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import com.nordpass.task.ui.base.BaseViewModel
import com.nordpass.task.ui.base.SingleLiveEvent
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.todolist.GetTodoListUseCase
import io.reactivex.rxkotlin.subscribeBy

class TodoListViewModel @ViewModelInject constructor(
    private val getTodoListUseCase: GetTodoListUseCase
) : BaseViewModel() {
    val items = MutableLiveData<List<Todo>>()

    val eventStream = SingleLiveEvent<Event>()

    fun init() {
        getTodoListUseCase.get()
            .subscribeBy(onSuccess = items::postValue, onError = ::handleError)
            .attach()
    }

    fun onItemClicked(todo: Todo) {
        eventStream.value = Event.GoToTodoDetails(todo)
    }

    sealed class Event {
        data class GoToTodoDetails(val todo: Todo) : Event()
    }
}