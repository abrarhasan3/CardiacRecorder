package android.example.cardiacrecorder;


import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.Espresso.onView;

import static org.hamcrest.CoreMatchers.anything;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import android.graphics.drawable.Drawable;
import android.widget.ProgressBar;

import androidx.core.content.ContextCompat;
import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@RunWith(AndroidJUnit4.class)
@LargeTest


public class LogInPageUITest {
    @Rule
    public ActivityScenarioRule<LogInPage> activityRule =
            new ActivityScenarioRule<>(LogInPage.class);

    @Test
    public void testLogInPage() {
        onView(withId(R.id.loginActivity)).check(matches(isDisplayed()));
        onView(withId(R.id.EMAIL)).check(matches(isDisplayed()));
        onView(withId(R.id.editTextTextPassword)).check(matches(isDisplayed()));
        onView(withId(R.id.Login)).check(matches(isDisplayed()));
        onView(withId(R.id.forget)).check(matches(isDisplayed()));
        onView(withId(R.id.EMAIL)).perform(typeText("user@gmail.com"));
        onView(withId(R.id.editText2)).perform(typeText("123456"));
        Espresso.pressBack();
        onView(withId(R.id.Login)).perform(click());
    }
}
