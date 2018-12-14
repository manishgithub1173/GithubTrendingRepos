package com.example.target

import android.content.Intent
import android.support.test.espresso.Espresso
import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.v7.widget.RecyclerView
import com.example.target.common.EspressoIdlingResource
import com.example.target.ui.main.MainActivity
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@MediumTest
@RunWith(JUnit4::class)
class UserDetailsFragmentTest {
    @get:Rule
    @JvmField
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
    fun testUserListClick() {
        Espresso.onView(ViewMatchers.withId(R.id.list))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    ViewActions.click()
                )
            )

        onView(withId(R.id.name)).check(matches(isDisplayed()))
        onView(withId(R.id.username)).check(matches(isDisplayed()))
        onView(withId(R.id.url)).check(matches(isDisplayed()))
        onView(withId(R.id.repoDetail)).check(matches(isDisplayed()))
        onView(withId(R.id.repoName)).check(matches(isDisplayed()))
        onView(withId(R.id.repoDesc)).check(matches(isDisplayed()))
        onView(withId(R.id.repoUrl)).check(matches(isDisplayed()))
    }
}