package com.nordpass.tt.usecase.todolist

import com.nhaarman.mockitokotlin2.whenever
import com.nordpass.tt.usecase.Todo
import io.reactivex.Single
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import org.threeten.bp.OffsetDateTime

class GetTodoListUseCaseTest {

    private val todoStorage = Mockito.mock(TodoStorage::class.java)

    private val sut = GetTodoListUseCase(todoStorage)

    @Before
    fun setUp() {
        whenever(todoStorage.getAll()).thenReturn(Single.just(provideTodoItems()))
    }

    @Test
    fun shouldReturnUnfinishedAndMostRecentlyUpdatedItemsAtTop() {
        // given
        val expectedItems = provideExpectedOrderedTodoItems()

        // when
        val actualItems = sut.get().blockingGet()

        // then
        assertThat(actualItems).isEqualTo(expectedItems)
    }

    private fun provideTodoItems(): List<Todo> = listOf(
        Todo(
            id = 1,
            title = "A",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-04-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "B",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-05-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "C",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-05-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "D",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-06-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "E",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-07-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "F",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-07-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "G",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-08-30T04:00:00.000Z"),
            updatedAt = null
        ),
    )

    private fun provideExpectedOrderedTodoItems(): List<Todo> = listOf(
        Todo(
            id = 1,
            title = "C",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-05-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "D",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-06-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "F",
            isCompleted = false,
            dueOn = OffsetDateTime.parse("2020-07-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "A",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-04-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "B",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-05-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "E",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-07-30T04:00:00.000Z"),
            updatedAt = null
        ),
        Todo(
            id = 1,
            title = "G",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-08-30T04:00:00.000Z"),
            updatedAt = null
        ),
    )
}