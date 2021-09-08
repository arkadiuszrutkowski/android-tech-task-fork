package com.nordpass.tt.usecase

import java.io.Serializable

data class Todo(
    val id: Int,
    val title: String,
    val isCompleted: Boolean,
    val updatedAt: String
) : Serializable