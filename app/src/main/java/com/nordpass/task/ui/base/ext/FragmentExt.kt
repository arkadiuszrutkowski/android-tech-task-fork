package com.nordpass.task.ui.base.ext

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.navigation.fragment.findNavController

const val NAVIGATION_RESULT_KEY = "result"

// Returning result to previous destination logic is taken from:
// https://developer.android.com/guide/navigation/navigation-programmatic
fun <T> Fragment.handleNavigationResult(@IdRes currentDestinationId: Int, callback: (T) -> Unit) {
    // After a configuration change or process death, the currentBackStackEntry
    // points to the dialog destination, so you must use getBackStackEntry()
    // with the specific ID of your destination to ensure we always
    // get the right NavBackStackEntry
    val navBackStackEntry = findNavController().getBackStackEntry(currentDestinationId)

    // Create our observer and add it to the NavBackStackEntry's lifecycle
    val observer = LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_RESUME) {
            val result = navBackStackEntry.savedStateHandle.get<T>(NAVIGATION_RESULT_KEY)
            result?.let { callback(it) }
            navBackStackEntry.savedStateHandle.remove<T>(NAVIGATION_RESULT_KEY)
        }
    }
    navBackStackEntry.lifecycle.addObserver(observer)

    // As addObserver() does not automatically remove the observer, we
    // call removeObserver() manually when the view lifecycle is destroyed
    viewLifecycleOwner.lifecycle.addObserver(LifecycleEventObserver { _, event ->
        if (event == Lifecycle.Event.ON_DESTROY) {
            navBackStackEntry.lifecycle.removeObserver(observer)
        }
    })
}

fun <T> Fragment.setNavigationResult(result: T) {
    findNavController().previousBackStackEntry?.savedStateHandle?.set(NAVIGATION_RESULT_KEY, result)
}
