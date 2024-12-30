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
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.Allure;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.customFile.CustomAndroidJUnit4Runner;

@LargeTest
@RunWith(CustomAndroidJUnit4Runner.class)
public class Scenario_Number_6 {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    // Отображение информации о приложении.
    @Test
    public void applicationInformation() {
        Allure.step("Ожидаем загрузку экрана логина", () -> {
            waitForView(R.id.login_text_input_layout, 15000);
        });

        Allure.step("Вводим логин и пароль", () -> {
            performClick(R.id.login_text_input_layout);
            typeTextInView(R.id.login_edit_text, "login2");
            performClick(R.id.password_text_input_layout);
            typeTextInView(R.id.password_edit_text, "password2");
        });

        Allure.step("Нажимаем кнопку 'Войти'", () -> {
            performClick(R.id.enter_button);
        });

        Allure.step("Открываем главное меню", () -> {
            waitForView(R.id.main_menu_image_button, 5000);
            performClick(R.id.main_menu_image_button);
        });

        Allure.step("Переходим в раздел 'О приложении'", () -> {
            performClickWithText("About");
            waitForView(R.id.about_privacy_policy_value_text_view, 15000);
        });

        Allure.step("Проверяем, что отображается текст 'Privacy Policy:'", () -> {
            checkViewIsDisplayed("Privacy Policy:");
        });
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
