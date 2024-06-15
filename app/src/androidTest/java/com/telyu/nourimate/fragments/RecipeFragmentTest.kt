package com.telyu.nourimate.fragments

import androidx.datastore.core.DataStore
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.IdlingResource
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.pressImeActionButton
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.hasDescendant
import androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.supportsInputMethods
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.NavigationBarActivity
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.allOf
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.prefs.Preferences

@RunWith(AndroidJUnit4ClassRunner::class)
class RecipeFragmentTest {

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
        onView(withId(R.id.nav_recipe)).perform(click())
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
    fun testMealTypeButtonInteractions() {

        onView(withId(R.id.button_daily)).perform(click())

        onView(withId(R.id.button_breakfast)).perform(click())
        onView(withId(R.id.breakfastRecyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.button_lunch)).perform(click())
        onView(withId(R.id.lunchRecyclerView)).check(matches(isDisplayed()))

        onView(withId(R.id.button_dinner)).perform(click())
        onView(withId(R.id.dinnerRecyclerView)).check(matches(isDisplayed()))
    }

    @Test
    fun testSearchBarFunctionality() {
        // Test for breakfastRecyclerView
        onView(withId(R.id.button_daily)).perform(click())
        onView(withId(R.id.button_breakfast)).perform(click())
        Thread.sleep(500)
        onView(withId(R.id.search_bar)).perform(click())
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.search_view))))
            .perform(typeText("nasi"), pressImeActionButton())
        Thread.sleep(1000)
        onView(withId(R.id.breakfastRecyclerView))
            .check(matches(hasDescendant(withText("Nasi Goreng Sayuran"))))

        // Test for lunchRecyclerView
        onView(withId(R.id.button_lunch)).perform(click()) // Assuming button_lunch is the ID for lunch button
        Thread.sleep(500)
        onView(withId(R.id.search_bar)).perform(click())
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.search_view))))
            .perform(clearText(), typeText("sushi"), pressImeActionButton())
        Thread.sleep(1000)
        onView(withId(R.id.lunchRecyclerView))
            .check(matches(hasDescendant(withText("Mangkuk Sushi Salmon"))))

        // Test for dinnerRecyclerView
        onView(withId(R.id.button_dinner)).perform(click()) // Assuming button_dinner is the ID for dinner button
        Thread.sleep(500)
        onView(withId(R.id.search_bar)).perform(click())
        onView(withId(R.id.search_view)).check(matches(isDisplayed()))
        onView(allOf(supportsInputMethods(), isDescendantOfA(withId(R.id.search_view))))
            .perform(clearText(), typeText("kacang"), pressImeActionButton())
        Thread.sleep(1000)
        onView(withId(R.id.dinnerRecyclerView))
            .check(matches(hasDescendant(withText("Protein Bar Kacang Tanah"))))

    }

    @Test
    fun testMealtimeToggle() {
        onView(withId(R.id.button_weekly)).perform(click())
        onView(withId(R.id.button_breakfast)).perform(click())
        onView(withId(R.id.weeklyRecommendationRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.button_lunch)).perform(click())
        onView(withId(R.id.weeklyRecommendationRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.button_dinner)).perform(click())
        onView(withId(R.id.weeklyRecommendationRecyclerView)).check(matches(isDisplayed()))

        // Toggle back to daily and check visibility
        onView(withId(R.id.button_daily)).perform(click())
        onView(withId(R.id.button_breakfast)).perform(click())
        onView(withId(R.id.breakfastRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.button_lunch)).perform(click())
        onView(withId(R.id.lunchRecyclerView)).check(matches(isDisplayed()))
        onView(withId(R.id.button_dinner)).perform(click())
        onView(withId(R.id.dinnerRecyclerView)).check(matches(isDisplayed()))
    }
}

class DataStoreIdlingResource(private val dataStore: DataStore<androidx.datastore.preferences.core.Preferences>) : IdlingResource, CoroutineScope by MainScope() {
    private var callback: IdlingResource.ResourceCallback? = null
    private var isIdleNow = true
        set(value) {
            if (value != field && value) {
                field = value
                callback?.onTransitionToIdle()
            }
        }

    init {
        // Observe changes in DataStore
        launch {
            dataStore.data.collect {
                isIdleNow = true
            }
        }
    }

    override fun getName(): String = DataStoreIdlingResource::class.java.name

    override fun isIdleNow(): Boolean = isIdleNow

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.callback = callback
    }
}
