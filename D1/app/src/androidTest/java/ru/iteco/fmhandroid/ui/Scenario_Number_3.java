package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.isPlatformPopup;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
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
public class Scenario_Number_3 {

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Test
    public void creationOfNewsInEnglish() {
        waitForViewToDisplay(R.id.main_menu_image_button, 5000);
        clickOnView(R.id.main_menu_image_button);
        clickOnText("News");

        waitForViewToDisplay(R.id.news_list_swipe_refresh, 5000);
        clickOnView(R.id.edit_news_material_button);

        waitForViewToDisplay(R.id.action_bar_root, 5000);
        clickOnView(R.id.add_news_image_view);

        waitForViewToDisplay(R.id.nav_host_fragment, 5000);
        waitForViewToDisplay(R.id.news_item_category_text_input_layout, 5000);

        selectCategory("Объявление");
        enterNewsTitle("New News");

        selectCurrentDate();
        selectCurrentTime();

        enterDescription("Checking text input in English");
        clickOnView(R.id.save_button);

        verifyNewsIsDisplayed("New News");
    }

    private void waitForViewToDisplay(int viewId, long timeout) {
        onView(isRoot()).perform(waitDisplayed(viewId, timeout));
    }

    private void clickOnView(int viewId) {
        onView(withId(viewId)).perform(click());
    }

    private void clickOnText(String text) {
        onView(withText(text)).perform(click());
    }

    private void selectCategory(String category) {
        clickOnView(R.id.news_item_category_text_input_layout);
        onView(withText(category)).inRoot(isPlatformPopup()).perform(click());
    }

    private void enterNewsTitle(String title) {
        waitForViewToDisplay(R.id.news_item_title_text_input_layout, 7000);
        onView(allOf(withId(R.id.news_item_title_text_input_edit_text),
                isDescendantOfA(withId(R.id.news_item_title_text_input_layout))))
                .perform(replaceText(""));
        clickOnView(R.id.news_item_title_text_input_edit_text);
        onView(withId(R.id.news_item_title_text_input_edit_text)).perform(typeText(title));
    }

    private void selectCurrentDate() {
        waitForViewToDisplay(R.id.news_item_create_date_text_input_layout, 7000);
        clickOnView(R.id.news_item_create_date_text_input_layout);
        onView(allOf(withText("OK"), isDescendantOfA(withId(android.R.id.content)))).perform(click());
    }

    private void selectCurrentTime() {
        waitForViewToDisplay(R.id.news_item_publish_time_text_input_layout, 7000);
        clickOnView(R.id.news_item_publish_time_text_input_layout);
        onView(allOf(withText("OK"), isDescendantOfA(withId(android.R.id.content)))).perform(click());
    }

    private void enterDescription(String description) {
        waitForViewToDisplay(R.id.news_item_description_text_input_layout, 7000);
        clickOnView(R.id.news_item_description_text_input_edit_text);
        onView(withId(R.id.news_item_description_text_input_edit_text)).perform(typeText(description));
    }

    private void verifyNewsIsDisplayed(String newsTitle) {
        waitForViewToDisplay(R.id.news_list_recycler_view, 7000);
        onView(withId(R.id.news_list_recycler_view))
                .perform(RecyclerViewActions.scrollTo(hasDescendant(withText(newsTitle))));
        onView(withText(newsTitle)).check(matches(isDisplayed()));
    }
}