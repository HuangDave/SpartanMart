package com.spartanmart;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.spartanmart.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;

import static android.support.test.espresso.Espresso.closeSoftKeyboard;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isEnabled;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Created by David on 11/4/16.
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class LoginActivityTests {

    @Rule
    public final ActivityTestRule<LoginActivity> mActivity = new ActivityTestRule<>(LoginActivity.class, false);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        //Context appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testInvalidLogin() throws IOException {
        final String falseEmail = "testemail@yahoo.com";
        final String password = "1234567";

        onView(withId(R.id.etEmail)).perform(typeText(falseEmail));
        onView(withId(R.id.etPassword)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.bLogin)).perform(click()).check(matches(isEnabled()));
    }

    @Test
    public void testValidLogin() throws IOException {
        final String email = "testemail@sjsu.edu";
        final String password = "1234567";

        onView(withId(R.id.etEmail)).perform(typeText(email));
        onView(withId(R.id.etPassword)).perform(typeText(password));
        closeSoftKeyboard();
        onView(withId(R.id.bLogin)).perform(click()).check(matches(not(isEnabled())));
    }
}
