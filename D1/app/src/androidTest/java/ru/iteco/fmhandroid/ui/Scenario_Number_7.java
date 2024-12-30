package ru.iteco.fmhandroid.ui;


import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;

import static ru.iteco.fmhandroid.ui.customFile.TestUtilities.waitDisplayed;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewAction;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario_Number_7 {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void LoveIsAll() {
        waitForView(R.id.our_mission_image_button, 15000);

        clickView(R.id.our_mission_image_button, "Our Mission");

        int[] itemPositions = {0, 2, 3, 1};
        for (int position : itemPositions) {
            performActionOnRecyclerViewItem(R.id.our_mission_item_list_recycler_view, position, click());
        }

        checkTextInView(
                R.id.our_mission_item_description_text_view,
                "Нет шаблона и стандарта, есть только дух, который живет в разных домах по-разному. Но всегда он добрый, любящий и помогающий."
        );
    }

    private void waitForView(int viewId, long duration) {
        onView(isRoot()).perform(waitDisplayed(viewId, duration));
    }

    private void clickView(int viewId, String description) {
        onView(allOf(
                withId(viewId),
                withContentDescription(description),
                isDisplayed()
        )).perform(click());
    }

    private void performActionOnRecyclerViewItem(int recyclerViewId, int position, ViewAction action) {
        onView(allOf(
                withId(recyclerViewId),
                isDisplayed()
        )).perform(actionOnItemAtPosition(position, action));
    }

    private void checkTextInView(int viewId, String expectedText) {
        onView(allOf(
                withId(viewId),
                withText(expectedText),
                isDisplayed()
        )).check(matches(withText(expectedText)));
    }

    private static Matcher<View> childAtPosition(final Matcher<View> parentMatcher, final int position) {
        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}