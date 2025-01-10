package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
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

import io.qameta.allure.Step;
import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class allureTest {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void Authorization_with_valid_data() {
        waitForViewToBeDisplayed(R.id.login_text_input_layout, 15000);
        performClickOnView(R.id.login_text_input_layout);
        enterTextInView(R.id.login_edit_text, "login2");
        performClickOnView(R.id.password_text_input_layout);
        enterTextInView(R.id.password_edit_text, "password2");
        performClickOnView(R.id.enter_button);
        waitForViewToBeDisplayed(R.id.container_list_news_include_on_fragment_main, 15000);
        verifyViewIsDisplayed("News");
    }

    @Step
    private void waitForViewToBeDisplayed(int viewId, long timeout) {
        onView(isRoot()).perform(waitDisplayed(viewId, timeout));
    }
    @Step
    private void performClickOnView(int viewId) {
        onView(withId(viewId)).perform(click());
    }
    @Step
    private void enterTextInView(int viewId, String text) {
        onView(withId(viewId)).perform(click(), typeText(text));
    }
    @Step
    private void verifyViewIsDisplayed(String text) {
        onView(withText(text)).check(matches(isDisplayed()));
    }
}
