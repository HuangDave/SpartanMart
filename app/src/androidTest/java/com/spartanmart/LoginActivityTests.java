package com.spartanmart;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.spartanmart.activities.LoginActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.action.ViewActions.replaceText;
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
    public ActivityTestRule<LoginActivity> mActivity = new ActivityTestRule<>(LoginActivity.class, false);

    @Test
    public void useAppContext() throws Exception {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getTargetContext();
    }

    @Test
    public void testUserLogin() {
        String falseEmail = "testemail@yahoo.com";
        String password = "1234567";

        String email = "testemail@sjsu.edu";

        onView(withId(R.id.etEmail)).perform(replaceText(falseEmail));
        onView(withId(R.id.etPassword)).perform(replaceText(password));
        onView(withId(R.id.bLogin)).perform(click()).check(matches(isEnabled()));

        onView(withId(R.id.etEmail)).perform(replaceText(email));
        onView(withId(R.id.etPassword)).perform(replaceText(password));
        onView(withId(R.id.bLogin)).perform(click()).check(matches(not(isEnabled())));
    }
}
