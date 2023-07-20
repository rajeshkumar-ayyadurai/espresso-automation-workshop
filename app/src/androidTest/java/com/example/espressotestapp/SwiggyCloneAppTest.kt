package com.example.espressotestapp

import androidx.test.espresso.Espresso.onView
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.example.swiggycloneapp.MainActivity
import com.example.swiggycloneapp.R
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.startsWith
import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*
import org.junit.Rule

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class SwiggyCloneAppTest {

    @get:Rule
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.example.swiggycloneapp", appContext.packageName)
    }

    @Test
    fun verifyHomeScreenIsVisible() {
        onView(
            allOf(
                isDescendantOfA(withId(R.id.rl_header)),
                withText("Home"))
        ).check(matches(isDisplayed()))
    }

    @Test
    fun verifyBottomTabNavigation() {
        onView(withId(R.id.tv_home)).perform(longClick())
        onView(withId(R.id.tv_home)).check(matches(isEnabled()))
        onView(withId(R.id.tv_food)).perform(click())
        onView(withText(startsWith("Top rated near you"))).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withContentDescription("Instamart")).perform(click())
        onView(withParent(withId(R.id.ll_banner))).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
    }

    @Test
    fun verifyVerticalScrolling() {
        onView(withId(R.id.tv_instamart)).perform(click())
        onView(withParent(withId(R.id.ll_banner))).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withText("Hot Deals!")).check(matches(isDisplayed()))
        onView(withId(R.id.sv_instamart)).perform(swipeUp())
        onView(withText("Thats all for now! :)")).check(matches(isDisplayed()))
    }

    @Test
    fun verifyHorizontalScrolling() {
        onView(withId(R.id.tv_food)).perform(click())
        onView(withText("Top rated near you")).check(matches(withEffectiveVisibility(Visibility.VISIBLE)))
        onView(withId(R.id.rv_toprated)).perform(swipeLeft())
        onView(withText("Dindigul Thalapakatti")).check(matches(isDisplayed()))
    }

}