package com.nordpass.task.ui.update

import com.nordpass.task.R
import com.nordpass.task.base.BaseRobot

fun updateTodoDetails(block: UpdateTodoDetailsRobot.() -> Unit) =
    UpdateTodoDetailsRobot().apply(block)

class UpdateTodoDetailsRobot : BaseRobot() {

    fun fillTodoTitle(title: String) {
        fillEditText(R.id.todo_title_input, title)
    }

    fun assertSaveEnabled(enabled: Boolean) {
        assertEnabled(R.id.save_button, enabled)
    }

    fun matchesTodoTitle(title: String) {
        matchesText(R.id.todo_title_input, title)
    }
}