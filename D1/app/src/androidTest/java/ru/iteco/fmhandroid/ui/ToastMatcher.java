package ru.iteco.fmhandroid.ui;

import android.widget.Toast;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

public class ToastMatcher extends TypeSafeMatcher<Toast> {

    @Override
    protected boolean matchesSafely(Toast toast) {
        // Проверка, что тост не null
        return toast != null;
    }

    @Override
    public void describeTo(Description description) {
        description.appendText("is Toast");
    }

    // Статический метод для удобства создания ToastMatcher
    public static Matcher<Toast> isToast() {
        return new ToastMatcher();
    }
}