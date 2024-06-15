package com.telyu.nourimate.activities

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.telyu.nourimate.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(LoginActivity::class.java)

    @Test
    fun testLoginFunctionality() {
        // Input valid email and password
        onView(withId(R.id.emailEditText)).perform(typeText("admin1@gmail.com"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("admin123"), closeSoftKeyboard())

        // Click login button
        onView(withId(R.id.buttonLogin)).perform(click())

        // Check for loading and successful login
        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
     //   intended(hasComponent(NavigationBarActivity::class.java.name))
       // onView(withText("Login Success")).inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(matches(isDisplayed()))
    }

    @Test
    fun testInvalidLoginAttempt() {
        // Input invalid email and password
        onView(withId(R.id.emailEditText)).perform(typeText("admin1"), closeSoftKeyboard())
        onView(withId(R.id.passwordEditText)).perform(typeText("admin123"), closeSoftKeyboard())

        // Click login button
        onView(withId(R.id.buttonLogin)).perform(click())

        // Check for loading and failure message
     //   onView(withId(R.id.progressBar)).check(matches(not(isDisplayed())))
       // onView(withText("Login Failed")).inRoot(withDecorView(not(activityRule.activity.window.decorView))).check(matches(isDisplayed()))
    }
}
