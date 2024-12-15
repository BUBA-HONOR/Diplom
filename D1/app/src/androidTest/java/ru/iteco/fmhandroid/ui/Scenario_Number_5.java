package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static ru.iteco.fmhandroid.ui.customFile.TestUtilities.waitDisplayed;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario_Number_5 {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    // Отображение версии приложения.
    @Test
    public void applicationInformation() {
        waitForView(R.id.login_text_input_layout, 15000);
        performClick(R.id.login_text_input_layout);
        typeTextInView(R.id.login_edit_text, "login2");
        performClick(R.id.password_text_input_layout);
        typeTextInView(R.id.password_edit_text, "password2");
        performClick(R.id.enter_button);
        waitForView(R.id.main_menu_image_button, 5000);
        performClick(R.id.main_menu_image_button);
        performClickWithText("About");
        waitForView(R.id.about_version_value_text_view, 15000);
        checkViewIsDisplayed("1.0.0");
    }

    private void waitForView(int viewId, long duration) {
        onView(isRoot()).perform(waitDisplayed(viewId, duration));
    }

    private void performClick(int viewId) {
        onView(withId(viewId)).perform(click());
    }

    private void typeTextInView(int viewId, String text) {
        onView(withId(viewId)).perform(click(), typeText(text));
    }

    private void performClickWithText(String text) {
        onView(withText(text)).perform(click());
    }

    private void checkViewIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }
}
