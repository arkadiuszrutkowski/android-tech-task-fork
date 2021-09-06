package com.nordpass.tt.usecase

import org.threeten.bp.OffsetDateTime
import java.io.Serializable

data class Todo(
    val id: Int,
    val title: String,
    val isCompleted: Boolean,
    val updatedAt: OffsetDateTime?,
    val dueOn: OffsetDateTime
) : Serializable