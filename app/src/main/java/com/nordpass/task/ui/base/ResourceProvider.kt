package com.nordpass.task.ui.base

import android.content.Context
import androidx.annotation.StringRes
import javax.inject.Inject

class ResourceProvider @Inject constructor(
    private val context: Context
) {

    fun getString(@StringRes resourceId: Int) = context.getString(resourceId)
}