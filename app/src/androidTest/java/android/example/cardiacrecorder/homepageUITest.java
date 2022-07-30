package android.example.cardiacrecorder;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.click;
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

public class homepageUITest {
    @Rule
    public ActivityScenarioRule<homepage> activityRule =
            new ActivityScenarioRule<>(homepage.class);

    @Test
    public void testHomePage()
    {
        onView(withId(R.id.homepage)).check(matches(isDisplayed()));
       onView(withId(R.id.CardView)).check(matches(isDisplayed()));
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
        onView(withId(R.id.TxtSystole)).check(matches(isDisplayed()));
        onView(withId(R.id.ProgressSystole)).check(matches(isDisplayed()));
        onView(withId(R.id.ProgressDiastole)).check(matches(isDisplayed()));
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
        onView(withId(R.id.textView7)).check(matches(isDisplayed()));
        onView(withId(R.id.TxtDayastole)).check(matches(isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.homepage)).perform(ViewActions.swipeUp());

        onView(withId(R.id.add_new)).check(matches(isDisplayed()));
        onView(withId(R.id.button3)).check(matches(isDisplayed()));
        onView(withId(R.id.add_new)).perform(click());
        onView(withText("ADD NEW DATA")).check(matches(isDisplayed()));
        onView(withId(R.id.heartrate)).check(matches(isDisplayed()));
        onView(withId(R.id.systolic)).check(matches(isDisplayed()));
        onView(withId(R.id.diastole)).check(matches(isDisplayed()));
        onView(withId(R.id.comment)).check(matches(isDisplayed()));
    }
}
