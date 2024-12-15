package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.customFile.TestUtilities.waitDisplayed;

import android.view.View;
import android.widget.Toast;

import androidx.test.core.app.ActivityScenario;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ru.iteco.fmhandroid.R;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class Scenario_Number_2 {
    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

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
    public void Authorization_with_no_valid_data() {
        // Ожидание для UI элемента
        onView(isRoot()).perform(waitDisplayed(R.id.login_text_input_layout, 7000));

        // Заполнение полей
        onView(withId(R.id.login_text_input_layout)).perform(click());
        onView(withId(R.id.login_edit_text)).perform(click(), typeText("log2"));
        onView(withId(R.id.password_text_input_layout)).perform(click());
        onView(withId(R.id.password_edit_text)).perform(click(), typeText("pass2"));

        // Закрытие клавиатуры
        closeSoftKeyboard();

        // Нажатие кнопки "Войти"
        onView(withId(R.id.enter_button)).perform(click());
    }
}
