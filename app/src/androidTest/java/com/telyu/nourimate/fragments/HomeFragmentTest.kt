package com.telyu.nourimate.fragments

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isSelected
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.telyu.nourimate.R
import com.telyu.nourimate.activities.NavigationBarActivity
import com.telyu.nourimate.data.local.models.NutritionSum
import com.telyu.nourimate.utils.UserModel
import com.telyu.nourimate.utils.UserPreference
import com.telyu.nourimate.utils.dataStore
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.not
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class HomeFragmentTest {

    @Rule
    @JvmField
    val activityRule = ActivityTestRule(NavigationBarActivity::class.java)

    private lateinit var idlingResource: DataStoreIdlingResource

    @Before
    fun setUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val userPreference = UserPreference.getInstance(context.dataStore)

        idlingResource = DataStoreIdlingResource(context.dataStore)
        IdlingRegistry.getInstance().register(idlingResource)

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

        onView(withId(R.id.nav_home)).perform(click())
    }

    @After
    fun tearDown() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val userPreference = UserPreference.getInstance(context.dataStore)
        runBlocking {
            userPreference.logout()
        }
        IdlingRegistry.getInstance().unregister(idlingResource)
    }

    @Test
    fun testGreetingMessagesBasedOnTimeOfDay() {

        // Mock system time to morning
        SystemTime.setFixedTime(7)
        onView(withId(R.id.greetingTextView)).check(matches(withText("Good morning,")))

        // Change time to afternoon
        SystemTime.setFixedTime(14)
        onView(withId(R.id.greetingTextView)).check(matches(withText("Good afternoon,")))

        // Change time to evening
        SystemTime.setFixedTime(19)
        onView(withId(R.id.greetingTextView)).check(matches(withText("Good evening,")))
    }

    @Test
    fun testBMIStatusUpdate() {
        // Navigate to HomeFragment (assuming it's the initial fragment or reachable from MainActivity)
        onView(withId(R.id.nav_home)).perform(ViewActions.click())

        // Define BMI values to test
        val bmiValues = listOf(17.0, 22.0, 24.0, 27.0)
        val expectedTexts = listOf("Underweight", "Normal", "Normal", "Obese")

        // Iterate through BMI values and perform checks
        bmiValues.forEachIndexed { index, bmi ->
            // Change BMI value
            updateBMI(bmi)

            // Check if text view displays correct status text
            onView(withId(R.id.speedTextView)).check(matches(withText(expectedTexts[index])))
        }
    }

    private fun updateBMI(bmi: Double) {
        // Simulate updating the BMI value in the UI
        val bmiTextView = Espresso.onView(withId(R.id.speedTextView))
        bmiTextView.perform(ViewActions.replaceText(bmi.toString()))
    }

    @Test
    fun testWaterTrackingFunctionality() {
        for (i in 0..3) {
            onView(withId(R.id.water1)).perform(click())
            onView(withId(R.id.textWaterIntake)).check(matches(withText("$i * 250 / 2000 mL")))
            onView(withId(R.id.textWaterPersentage)).check(matches(withText("$i * 12.5 %")))
        }

        for (i in 4..5) {
            onView(withId(R.id.water1)).perform(click())
            onView(withId(R.id.textWaterIntake)).check(matches(withText("$i * 250 / 2000 mL")))
            onView(withId(R.id.textWaterPersentage)).check(matches(withText("$i * 12.5 %")))
        }

        onView(withId(R.id.water1)).check(matches(isSelected()))
        onView(withId(R.id.water2)).check(matches(isSelected()))
        onView(withId(R.id.water3)).check(matches(isSelected()))
        onView(withId(R.id.water4)).check(matches(isSelected()))
        onView(withId(R.id.water5)).check(matches(not(isSelected())))
        onView(withId(R.id.water6)).check(matches(not(isSelected())))
        onView(withId(R.id.water7)).check(matches(not(isSelected())))
        onView(withId(R.id.water8)).check(matches(not(isSelected())))

        onView(withId(R.id.textWaterIntake)).check(matches(withText("1000 / 2000 mL")))
        onView(withId(R.id.textWaterPersentage)).check(matches(withText("50 %")))
    }

    @Test
    fun testNutritionValuesSwitchingUsers() {
        onView(withId(R.id.nav_home)).perform(click())

        val userTests = listOf(
            UserTest(1, NutritionSum(200.0, 10.0, 15.0, 30.0), NutritionSum(300.0, 20.0, 25.0, 40.0)),
            UserTest(2, NutritionSum(250.0, 15.0, 20.0, 35.0), NutritionSum(350.0, 25.0, 30.0, 45.0))
        )

        userTests.forEach { test ->
            onView(withId(R.id.textViewTotalCalories)).perform(replaceText(test.expectedNutrition.totalCalories.toString()))
            onView(withId(R.id.textViewTotalProtein)).perform(replaceText(test.expectedNutrition.totalProtein.toString()))
            onView(withId(R.id.textViewTotalFat)).perform(replaceText(test.expectedNutrition.totalFat.toString()))
            onView(withId(R.id.textViewTotalCarbs)).perform(replaceText(test.expectedNutrition.totalCarbs.toString()))

            onView(withId(R.id.textViewGoalCalories)).perform(replaceText("/" + test.expectedMaxNutrition.totalCalories.toString()))
            onView(withId(R.id.textViewGoalProtein)).perform(replaceText("/" + test.expectedMaxNutrition.totalProtein.toString()))
            onView(withId(R.id.textViewGoalFat)).perform(replaceText("/" + test.expectedMaxNutrition.totalFat.toString()))
            onView(withId(R.id.textViewGoalCarbs)).perform(replaceText("/" + test.expectedMaxNutrition.totalCarbs.toString()))

            // Check each displayed value matches expected
            onView(withId(R.id.textViewTotalCalories)).check(matches(withText("${test.expectedNutrition.totalCalories}")))
            onView(withId(R.id.textViewTotalProtein)).check(matches(withText("${test.expectedNutrition.totalProtein}")))
            onView(withId(R.id.textViewTotalFat)).check(matches(withText("${test.expectedNutrition.totalFat}")))
            onView(withId(R.id.textViewTotalCarbs)).check(matches(withText("${test.expectedNutrition.totalCarbs}")))
        }
    }

    private fun replaceText(value: String): ViewAction {
        return ViewActions.replaceText(value)
    }
}

    object SystemTime {
        private var fixedTime: Long = 0

        fun setFixedTime(hourOfDay: Int) {
            fixedTime = hourOfDay * 3600000L
        }
    }

data class UserTest(
    val userId: Int,
    val expectedNutrition: NutritionSum,
    val expectedMaxNutrition: NutritionSum
)
