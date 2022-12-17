package com.pns.trending.ui.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.activityScenarioRule
import com.pns.trending.R
import org.junit.Rule
import org.junit.Test

class MainActivityTest {

    @get:Rule
    val activityScenarioRule = activityScenarioRule<MainActivity>()

    @Test
    fun testTitleTextview_expectedCorrectText() {

        onView(withId(R.id.main_title)).check(matches(withText("Trending")))
    }
}