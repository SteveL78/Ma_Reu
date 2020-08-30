package lamzone.com.controller;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lamzone.com.R;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        // On clique sur le FAB pour créer une nouvelle réunion
        ViewInteraction floatingActionButton = onView(
                allOf(withId(R.id.fab_btn),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()));
        floatingActionButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On indique le sujet de la réunion
        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.meeting_subject_editText),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                0)));
        appCompatEditText.perform(scrollTo(), replaceText("Communication"), closeSoftKeyboard());

        // On clique pour définir la date de début
        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.select_date_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        1),
                                1)));
        appCompatButton.perform(scrollTo(), click());

        // On sélectionne un autre mois...
        ViewInteraction appCompatImageButton = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Next month"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        // ... et on sélectionne l'heure de début ...
        ViewInteraction appCompatButton2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton2.perform(scrollTo(), click());

        // ... et les minutes.
        ViewInteraction appCompatButton3 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton3.perform(scrollTo(), click());

        // On clique pour définir l'heure de fin...
        ViewInteraction appCompatButton4 = onView(
                allOf(withId(R.id.select_end_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        2),
                                1)));
        appCompatButton4.perform(scrollTo(), click());

        // ... on sélectionne l'heure de fin
        ViewInteraction appCompatButton5 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton5.perform(scrollTo(), click());

        // On clique pour sélectionner la salle ...
        ViewInteraction appCompatButton6 = onView(
                allOf(withId(R.id.room_btn),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        3),
                                1)));
        appCompatButton6.perform(scrollTo(), click());

        // .. on sélectionne la 10ème salle (donc à la position 9) ...
        DataInteraction appCompatCheckedTextView = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(9);
        appCompatCheckedTextView.perform(click());

        // ... puis on valide en cliquant sur "Ok"
        ViewInteraction appCompatButton7 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton7.perform(scrollTo(), click());

/*        // On sélectionne les participants donc on tape la lettre C pour Charles ...
        ViewInteraction appCompatMultiAutoCompleteTextView = onView(
                allOf(withId(R.id.multiautocompletetextview),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatMultiAutoCompleteTextView.perform(scrollTo(), replaceText("c"), closeSoftKeyboard());

        DataInteraction appCompatTextView = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(0);
        appCompatTextView.perform(click());*/




        onView(withId(R.id.multiautocompletetextview)).perform(click());
        onView(withId(R.id.multiautocompletetextview)).perform(clearText(), typeText("c"));
        onView(withText("Charles")).inRoot(RootMatchers.isPlatformPopup()).perform(click());


        onView(withId(R.id.multiautocompletetextview)).perform(click());
        onView(withId(R.id.multiautocompletetextview)).perform(typeText("c"));
        onView(withText("Cassandra")).inRoot(RootMatchers.isPlatformPopup()).perform(click());














/*        onView(withId(R.id.multiautocompletetextview))
                .perform(typeText("c"));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        onData(anything())
                .atPosition(0)
                .perform(click());

        onView(withId(R.id.multiautocompletetextview))
                .perform(typeText("c"));
        onData(anything()).atPosition(1).perform(click());*/



       /* // ... on le sélectionne dans la liste ...
        ViewInteraction appCompatMultiAutoCompleteTextView2 = onView(
                allOf(withId(R.id.multiautocompletetextview), withText("Charles, "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatMultiAutoCompleteTextView2.perform(scrollTo(), replaceText("Charles, c"));

        // ... puis on clique de nouveau sur C pour Cassandra ...
        ViewInteraction appCompatMultiAutoCompleteTextView3 = onView(
                allOf(withId(R.id.multiautocompletetextview), withText("Charles, c"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatMultiAutoCompleteTextView3.perform(closeSoftKeyboard());

        DataInteraction appCompatTextView2 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatTextView2.perform(click());

        // Charles et Cassandra sont ajoutés à la liste
        ViewInteraction appCompatMultiAutoCompleteTextView4 = onView(
                allOf(withId(R.id.multiautocompletetextview), withText("Charles, Cassandra, "),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1)));
        appCompatMultiAutoCompleteTextView4.perform(scrollTo(), replaceText("Charles, Cassandra, s"));

        ViewInteraction appCompatMultiAutoCompleteTextView5 = onView(
                allOf(withId(R.id.multiautocompletetextview), withText("Charles, Cassandra, s"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.LinearLayout")),
                                        4),
                                1),
                        isDisplayed()));
        appCompatMultiAutoCompleteTextView5.perform(closeSoftKeyboard());

        DataInteraction appCompatTextView3 = onData(anything())
                .inAdapterView(childAtPosition(
                        withClassName(is("android.widget.PopupWindow$PopupBackgroundView")),
                        0))
                .atPosition(1);
        appCompatTextView3.perform(click());*/

        // On clique sur enregistrer pour finaliser la crétion de la réunion
        ViewInteraction appCompatButton8 = onView(
                allOf(withId(R.id.save_btn), withText("Enregistrer"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                6)));
        appCompatButton8.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
/*        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction overflowMenuButton = onView(
                allOf(withContentDescription("Lala"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        overflowMenuButton.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView4 = onView(
                allOf(withId(R.id.title), withText("par salle"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView4.perform(click());

        DataInteraction appCompatCheckedTextView2 = onData(anything())
                .inAdapterView(allOf(withClassName(is("com.android.internal.app.AlertController$RecycleListView")),
                        childAtPosition(
                                withClassName(is("android.widget.FrameLayout")),
                                0)))
                .atPosition(9);
        appCompatCheckedTextView2.perform(click());

        ViewInteraction appCompatButton9 = onView(
                allOf(withId(android.R.id.button1), withText("Ok"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton9.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction overflowMenuButton2 = onView(
                allOf(withContentDescription("Lala"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        overflowMenuButton2.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView5 = onView(
                allOf(withId(R.id.title), withText("par date"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView5.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withClassName(is("androidx.appcompat.widget.AppCompatImageButton")), withContentDescription("Next month"),
                        childAtPosition(
                                allOf(withClassName(is("android.widget.DayPickerView")),
                                        childAtPosition(
                                                withClassName(is("com.android.internal.widget.DialogViewAnimator")),
                                                0)),
                                2),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction appCompatButton10 = onView(
                allOf(withId(android.R.id.button1), withText("OK"),
                        childAtPosition(
                                childAtPosition(
                                        withClassName(is("android.widget.ScrollView")),
                                        0),
                                3)));
        appCompatButton10.perform(scrollTo(), click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction overflowMenuButton3 = onView(
                allOf(withContentDescription("Lala"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                1),
                        isDisplayed()));
        overflowMenuButton3.perform(click());

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ViewInteraction appCompatTextView6 = onView(
                allOf(withId(R.id.title), withText("Réinitialiser les filtres"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.content),
                                        0),
                                0),
                        isDisplayed()));
        appCompatTextView6.perform(click());*/
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
