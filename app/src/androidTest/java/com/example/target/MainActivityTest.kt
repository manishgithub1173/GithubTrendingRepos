package com.example.target

import android.content.Intent
import org.junit.Before
import org.junit.Rule
import android.support.test.runner.AndroidJUnit4
import org.junit.runner.RunWith
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import com.example.target.ui.main.MainActivity
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import com.example.target.common.EspressoIdlingResource
import android.support.test.espresso.IdlingRegistry
import org.junit.After

@MediumTest
@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule @JvmField
    var activityTestRule: ActivityTestRule<MainActivity> =
        ActivityTestRule(MainActivity::class.java, true, false)

    @Before
    @Throws(Exception::class)
    fun setUp() {
        activityTestRule.launchActivity(Intent())
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun testTrendingFragment() {
        onView(withId(R.id.trendingRepoContainer)).check(matches((isDisplayed())))
    }
}