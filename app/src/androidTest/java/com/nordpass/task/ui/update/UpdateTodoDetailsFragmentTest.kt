package com.nordpass.task.ui.update

import androidx.core.os.bundleOf
import com.nordpass.task.di.launchFragmentInHiltContainer
import com.nordpass.tt.usecase.Todo
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.OffsetDateTime

@HiltAndroidTest
class UpdateTodoDetailsFragmentTest {

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Test
    fun shouldPrefillTodoTitleInEditText() {
        // given
        launchFragmentInHiltContainer<UpdateTodoDetailsFragment>(provideFragmentArgs())

        // when & then
        updateTodoDetails {
            matchesTodoTitle("Test title")
        }
    }

    @Test
    fun shouldDetermineCorrectSaveButtonStateBasedOnTodoTitle() {
        // given
        launchFragmentInHiltContainer<UpdateTodoDetailsFragment>(provideFragmentArgs())

        // when & then
        updateTodoDetails {
            fillTodoTitle("")
            assertSaveEnabled(false)
            fillTodoTitle("New title")
            assertSaveEnabled(true)
        }
    }

    private fun provideFragmentArgs() = bundleOf(
        "todo" to Todo(
            id = 1,
            title = "Test title",
            isCompleted = true,
            dueOn = OffsetDateTime.parse("2020-04-30T04:00:00.000Z"),
            updatedAt = null
        )
    )
}
