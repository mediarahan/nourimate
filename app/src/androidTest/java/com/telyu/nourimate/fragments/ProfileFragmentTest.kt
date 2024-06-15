package com.telyu.nourimate.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.rule.ActivityTestRule
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.NavigationBarActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProfileFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(NavigationBarActivity::class.java)

    @Before
    fun setup() {
        // Navigate to the ProfileFragment initially
        onView(withId(R.id.nav_profile)).perform(click())
    }

    @Test
    fun testChangeProfilePhotoDialog() {
        // Click on the profile photo change button
        onView(withId(R.id.cardViewAddAvatar)).perform(click())

        // Verify the dialog and its options appear
        onView(withText("Choose Image Source")).check(matches(isDisplayed()))
        onView(withText("Camera")).check(matches(isDisplayed()))
        onView(withText("Gallery")).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationToSettings() {
        // Click on the settings icon
        onView(withId(R.id.settingsIcon)).perform(click())

        // Verify elements in the settings activity
        onView(withId(R.id.spinnerThemes)).check(matches(isDisplayed()))
        onView(withId(R.id.switchPreferencesRecipeTransition)).check(matches(isDisplayed()))
        onView(withId(R.id.switchNotification)).check(matches(isDisplayed()))
    }

    @Test
    fun testLogoutConfirmationDialog() {
        // Click on the logout button
        onView(withId(R.id.SignOutButton)).perform(click())

        // Check if the dialog appears and has the correct text
        onView(withText("Are you sure you want to logout?")).check(matches(isDisplayed()))
        onView(withText("Cancel")).perform(click())
    }

    @Test
    fun testNavigationToUserDetailFragment() {
        // Click on the profile detail button
        onView(withId(R.id.profileButton)).perform(click())

        // Verify the user details screen is displayed
        onView(withId(R.id.editTextName)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextBirth)).check(matches(isDisplayed()))
        onView(withId(R.id.spinnerGender)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextWeight)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextHeight)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSaveEditProfile)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.editTextWaist)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextDisease)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextAllergy)).check(matches(isDisplayed()))
        onView(withId(R.id.buttonSaveEditProfile)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationToAccountFragment() {
        // Click on the account button
        onView(withId(R.id.accountButton)).perform(click())

        // Verify the account details are displayed
        onView(withId(R.id.editTextEmail)).check(matches(isDisplayed()))
        onView(withId(R.id.editTextPhone)).check(matches(isDisplayed()))
    }

    @Test
    fun testNavigationToHistoryFragment() {
        // Click on the history button
        onView(withId(R.id.historyButton)).perform(click())

        // Verify the history screen is displayed
        onView(withId(R.id.historyrecyclerview)).check(matches(isDisplayed()))
    }
}
