package ru.iteco.fmhandroid.ui.customFile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import androidx.test.espresso.matcher.RootMatchers;


public class AuthorizationPage {
    public void checkToastMessage(String text) {
        onView(withText(text))
                .inRoot(RootMatchers.isPlatformPopup())
                .check(matches(isDisplayed()));
    }
}