package com.nordpass.task.base

import androidx.annotation.IdRes
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import org.hamcrest.Matchers.not

abstract class BaseRobot {

    fun matchesText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).check(matches(withText(text)))
    }

    fun fillEditText(@IdRes viewId: Int, text: String) {
        onView(withId(viewId)).perform(replaceText(text), closeSoftKeyboard())
    }

    fun assertEnabled(@IdRes viewId: Int, enabled: Boolean) {
        if (enabled) {
            onView(withId(viewId)).check(matches(isEnabled()))
        } else {
            onView(withId(viewId)).check(matches(not(isEnabled())))
        }
    }
}