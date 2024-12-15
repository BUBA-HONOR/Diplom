package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static ru.iteco.fmhandroid.ui.customFile.TestUtilities.waitDisplayed;

import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario_Number_6 {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    // Раскрытие текста записей.
    @Test
    public void Editing_news() {
        waitForView(R.id.our_mission_image_button, 15000);
        performClick(R.id.our_mission_image_button);
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(2, click()));
    }
    private void waitForView(int viewId, long duration) {
        onView(isRoot()).perform(waitDisplayed(viewId, duration));
    }
    private void performClick(int viewId) {
        onView(withId(viewId)).perform(click());
    }
}
