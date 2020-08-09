package lamzone.com;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.DatePicker;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.hamcrest.TypeSafeMatcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lamzone.com.controller.MainActivity;
import lamzone.com.di.DI;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.core.internal.deps.dagger.internal.Preconditions.checkNotNull;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.hasMinimumChildCount;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
   /* private MainActivity mActivity;
    private static int ITEMS_COUNT = 12;


    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class);


    @Before
    public void setUp() throws Exception {
        mActivity = mActivityRule.getActivity();
        assertThat(mActivity, notNullValue());
        mActivity.adapter.mMeetings = DI.getNewInstanceApiService().getMeetings();
    }
    @After
    public void finish(){
        mActivity.finish();
        mActivity.adapter.mMeetings.clear();
    }

    @Test
    public void myMeetingsListIsFiltered_byRoom_withSuccess(){
        onView(allOf(withId(R.id.filter_icon), isDisplayed()))
                .perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed()))
                .check(withItemCount(3));
        for(int i = 0; i <= 2 ;i++){
            onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed()))
                    .check(matches(atPosition(i, hasDescendant(withText("Mario")))));
        }

    }

    @Test
    public void myMeetingsListFilter_isReset_withSuccess(){
        onView(allOf(withId(R.id.filter_icon), isDisplayed()))
                .perform(click());
        onData(anything()).atPosition(0).perform(click());
        onData(anything()).atPosition(1).perform(click());
        onView(allOf(withId(R.id.filter_icon), isDisplayed()))
                .perform(click());
        onData(anything()).atPosition(2).perform(click());
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed()))
                .check(withItemCount(ITEMS_COUNT));

    }


    @Test
    public void myMeetingsList_shouldNotBeEmpty(){
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed()))
                .check(matches(hasMinimumChildCount(1)));
    }

    @Test
    public void myMeetingsList_deleteAction_shouldRemoveItem(){
        // Given : We remove the element at position 2
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT));
        // When perform a click on a delete icon
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition(1, new DeleteViewAction()));
        // Then : the number of element is 11
        onView(allOf(withId(R.id.meetings_list_recyclerView), isDisplayed())).check(withItemCount(ITEMS_COUNT-1));
    }

    @Test
    public void createMeetingWithSuccess(){
        ViewInteraction floatingActionButton = onView(
                Matchers.allOf(withId(R.id.fab_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.FrameLayout")),
                                        0),
                                0),
                        isDisplayed()));
        floatingActionButton.perform(click());

        ViewInteraction textInputEditText = onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.meeting_subject_editText),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText.perform(replaceText("Tests"), closeSoftKeyboard());

        ViewInteraction appCompatSpinner = onView(
                Matchers.allOf(withId(R.id.room_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        appCompatSpinner.perform(click());

        DataInteraction textView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(3);
        textView.perform(click());

        ViewInteraction textInputEditText2 = onView(
                Matchers.allOf(childAtPosition(
                        childAtPosition(
                                withId(R.id.select_date_btn),
                                0),
                        0),
                        isDisplayed()));
        textInputEditText2.perform(click());
        onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
                .perform(PickerActions.setDate(2020, 1, 15));
        onView(withId(android.R.id.button1)).perform(click());



    }*/

}
