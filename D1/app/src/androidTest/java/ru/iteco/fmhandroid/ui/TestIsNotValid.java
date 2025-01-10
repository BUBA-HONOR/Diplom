package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.replaceText;



import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static ru.iteco.fmhandroid.ui.customFile.TestUtilities.waitDisplayed;

import android.view.View;

import androidx.test.core.app.ActivityScenario;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.customFile.AuthorizationPage;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TestIsNotValid {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);
    private AuthorizationPage authorizationPage;

    private View decorView;
    @Before
    public void setUp() {
        mActivityScenarioRule.getScenario().onActivity(new ActivityScenario.ActivityAction<AppActivity>() {
            @Override
            public void perform(AppActivity activity) {
                decorView = activity.getWindow().getDecorView();
            }
        });
    }

    @Test
    public void authorizationBasedOnInvalidData() { // Авторизация по не валидным данным .
        // Вводим неверные данные
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 7000));
        onView(withId(R.id.login_edit_text)).perform(replaceText("wrong_login"));
        onView(withId(R.id.password_edit_text)).perform(replaceText("wrong_password"));
        // Закрытие клавиатуры
        closeSoftKeyboard();
        // Нажимаем на кнопку "Войти"
        onView(withId(R.id.enter_button)).perform(click());

//        onView(withText("Something went wrong. Try again later."))
//                .inRoot(withDecorView(not(decorView)))
//                .check(matches(isDisplayed()));

        authorizationPage.checkToastMessage("Something went wrong. Try again later.");
    }
}
