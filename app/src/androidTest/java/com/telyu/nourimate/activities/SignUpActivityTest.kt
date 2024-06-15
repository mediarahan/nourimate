package com.telyu.nourimate.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.telyu.nourimate.R
import org.junit.Assert.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class SignUpActivityTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(SignUpActivity::class.java)

    @Test
    fun testUserInputDisplay() {
        onView(withId(R.id.editTextPassword)).perform(typeText("admin1"), closeSoftKeyboard())
        onView(withId(R.id.editTextFullName)).perform(typeText("Admin"), closeSoftKeyboard())
        onView(withId(R.id.editTextEmail)).perform(typeText("admin123@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.editTextConfirmPassword)).perform(scrollTo(), typeText("admin1"), closeSoftKeyboard())

        onView(withId(R.id.editTextFullName)).check(matches(withText("Admin")))
        onView(withId(R.id.editTextEmail)).check(matches(withText("admin123@gmail.com")))
        onView(withId(R.id.editTextPassword)).check(matches(withText("admin1")))
        onView(withId(R.id.editTextConfirmPassword)).check(matches(withText("admin1")))

        onView(withId(R.id.buttonRegister)).perform(scrollTo(), click())

       // onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

}