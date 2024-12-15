package ru.iteco.fmhandroid.ui.customFile;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import ru.iteco.fmhandroid.R;

public class AuthorizationPage {
    private final int loginFieldId = R.id.login_text_input_layout;

    public void waitAuthorizationPage() {
        onView(isRoot()).perform(TestUtilities.waitDisplayed(loginFieldId, 7000));
    }
}

