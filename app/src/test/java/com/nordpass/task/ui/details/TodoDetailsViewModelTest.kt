package com.nordpass.task.ui.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import com.nhaarman.mockitokotlin2.*
import com.nordpass.tt.usecase.Todo
import com.nordpass.tt.usecase.common.TimeProvider
import com.nordpass.tt.usecase.todolist.UpdateTodoItemUseCase
import io.reactivex.Completable
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.OffsetDateTime

class TodoDetailsViewModelTest {

    @get:Rule
    val instantTaskExecRule = InstantTaskExecutorRule()

    private val updateTodoItemUseCase = mock<UpdateTodoItemUseCase>().apply {
        whenever(update(any())).thenReturn(Completable.complete())
    }
    private val savedStateHandle = mock<SavedStateHandle>().apply {
        whenever(getLiveData<Todo>("item")).thenReturn(MutableLiveData())
    }
    private val timeProvider = mock<TimeProvider>().apply {
        whenever(now()).thenReturn(OffsetDateTime.parse("2020-04-30T04:01:00.000Z"))
    }

    private val sut = TodoDetailsViewModel(savedStateHandle, timeProvider, updateTodoItemUseCase)

    @Test
    fun shouldAssignValueToTodoLiveDataOnInit() {
        // given
        val expectedItem = provideExampleTodo()

        // when
        sut.init(provideExampleTodo())

        // then
        sut.item.observeForever { actualItem -> assertEquals(expectedItem, actualItem) }
    }

    @Test
    fun shouldUpdateCompletionStateInStorageAndLiveDataAfterChangingToUnfinished() {
        // given
        val expectedItem = provideExampleTodo().copy(
            isCompleted = false,
            updatedAt = OffsetDateTime.parse("2020-04-30T04:01:00.000Z")
        )
        sut.init(provideExampleTodo())

        // when
        sut.onTodoClicked()

        // then
        sut.item.observeForever { actualItem -> assertEquals(expectedItem, actualItem) }
        verify(updateTodoItemUseCase).update(eq(expectedItem))
    }

    private fun provideExampleTodo() = Todo(
        id = 1,
        title = "Test title",
        isCompleted = true,
        dueOn = OffsetDateTime.parse("2020-04-30T04:00:00.000Z"),
        updatedAt = null
    )
}