package ru.iteco.fmhandroid.ui.customFile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;


import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewAssertion;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    private final int loginFieldId = R.id.login_text_input_layout;

    public void waitAuthorizationPage() {
        onView(isRoot()).perform(TestUtilities.waitDisplayed(loginFieldId, 7000));
    }
    private View decorView;

    public AuthorizationPage(View decorView) {
        this.decorView = decorView;
    }

    public void checkToastMessage(String text) {
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))  // Проверка Toast вне текущего decorView
                .check(matches(isDisplayed()));
    }
}