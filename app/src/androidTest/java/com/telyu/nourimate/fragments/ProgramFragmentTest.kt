package com.telyu.nourimate.fragments

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.scrollTo
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withSpinnerText
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.NavigationBarActivity
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.containsString
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4ClassRunner::class)
class ProgramFragmentTest {
    @Rule
    @JvmField
    val activityRule = ActivityTestRule(NavigationBarActivity::class.java)

    private lateinit var idlingResource: DataStoreIdlingResource

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val userPreference = UserPreference.getInstance(context.dataStore)

        // Initialize the idling resource
        idlingResource = DataStoreIdlingResource(context.dataStore)
        IdlingRegistry.getInstance().register(idlingResource)

        // Populate DataStore
        runBlocking {
            userPreference.saveSession(
                UserModel(
                    id = 1,
                    email = "test@example.com",
                    accessToken = "access-token",
                    refreshToken = "refresh-token",
                    isLoggedIn = true,
                    isVerified = true,
                    isDetailFilled = true,
                    name = "Test User",
                    phoneNumber = "1234567890"
                )
            )
        }

        // Navigate to the RecipeFragment initially
        onView(withId(R.id.nav_program)).perform(click())
    }

    @After
    fun tearDown() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val userPreference = UserPreference.getInstance(context.dataStore)
        runBlocking {
            userPreference.logout()  // Clear the session after tests
        }
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testProgramDisplayTransition() {
        // Navigate to ProgramFragment
        onView(withId(R.id.nav_program)).perform(click())

        // Check for elements when ongoingProgram is 0
        onView(withId(R.id.programContentFrame)).check(matches(hasDescendant(withId(R.id.ErrorImageView))))
        onView(withId(R.id.ErrorImageView)).check(matches(isDisplayed()))

        onView(withId(R.id.subtitleErrorTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.subtitleErrorTextView)).check(matches(withText("You haven't registered in any programs.")))

        onView(withId(R.id.subtitleNewProgramTextView)).check(matches(isDisplayed()))
        onView(withId(R.id.subtitleNewProgramTextView)).check(matches(withText("Please choose the program.")))

        onView(withId(R.id.menuIcon)).perform(click())
        onView(withId(R.id.btnRestoreProgram)).check(matches(isDisplayed()))

    }

    @Test
    fun testProgramDetailsDisplay() {
        onView(withId(R.id.buttonChooseProgram)).perform(click())

        onView(withId(R.id.spinnerProgram)).perform(click())
        onView(withText("Maintain Weight")).perform(click())

        onView(withId(R.id.editTextDateOfProgram)).perform(replaceText("2024/06/15 to 2024/07/15"), closeSoftKeyboard())
        onView(withId(R.id.buttonSelectProgram)).perform(click())


        Thread.sleep(3000)
//        onView(withId(R.id.titleTextView)).check(matches(withText(containsString("Maintain Weight"))))
//        onView(withId(R.id.subtitleTextView)).check(matches(withText(containsString("2024/06/15 to 2024/07/15"))))
//        onView(withId(R.id.textViewStartingWeightMW)).check(matches(withText("70 kg")))
//        onView(withId(R.id.textViewCurrentWeightMW)).check(matches(withText("75 kg")))
    }

    @Test
    fun testFinalizeProgramFunctionality() {
        onView(withId(R.id.menuIcon)).perform(click())

        onView(withId(R.id.btnRestoreProgram)).perform(click())

        onView(withText("Confirm Restore")).check(matches(isDisplayed()))
        onView(withText("End the current program and save your results?")).check(matches(isDisplayed()))  // Check dialog message

        onView(withText("Yes")).check(matches(isDisplayed()))
        onView(withText("No")).check(matches(isDisplayed()))
    }



}
