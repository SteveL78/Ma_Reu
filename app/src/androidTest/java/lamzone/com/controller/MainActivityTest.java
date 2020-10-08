package lamzone.com.controller;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import lamzone.com.R;
import lamzone.com.utils.TestUtils;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static lamzone.com.utils.TestUtils.withRecyclerView;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void mainActivityTest() {

        // On clique sur le FAB pour créer une nouvelle réunion
        onView(withId(R.id.fab_btn)).perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On indique le sujet de la réunion
        onView(withId(R.id.meeting_subject_editText)).perform(replaceText("Communication"), closeSoftKeyboard());


        // On clique pour définir la date de début
        onView(withId(R.id.select_date_btn)).perform(click());

        // ... et on sélectionne l'heure de début ...
        onView(withText("OK")).perform(click());

        // ... et les minutes.
        onView(withText("OK")).perform(click());


        // On clique sur le bouton pour définir l'heure de fin...
        onView(withId(R.id.select_end_btn)).perform(click());

        // ... on sélectionne l'heure de fin (heure actuelle)
        onView(withText("OK")).perform(click());


        // On sélectionne la salle Funky Kong...
        onView(withId(R.id.room_btn)).perform(click());
        onView(withText("Funky Kong")).perform(click());
        onView(withText("OK")).perform(click());


        // On sélectionne les participants dans multiautocompletetextview
        onView(withId(R.id.multiautocompletetextview)).perform(click());
        onView(withId(R.id.multiautocompletetextview)).perform(clearText(), typeText("c"));
        onView(withText("Charles")).inRoot(RootMatchers.isPlatformPopup()).perform(click());

        onView(withId(R.id.multiautocompletetextview)).perform(click());
        onView(withId(R.id.multiautocompletetextview)).perform(typeText("c"));
        onView(withText("Cassandra")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withId(R.id.multiautocompletetextview)).perform(closeSoftKeyboard());


        // On clique sur enregistrer pour finaliser la création de la réunion
        onView(withId(R.id.save_btn)).perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(700);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On sélectionne le filtre par salle ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("par salle")).perform(click());
        onView(withText("Funky Kong")).perform(click());
        onView(withText("OK")).perform(click());

        //... et on vérifie que la liste contient bien une réunion avec pour objet "Communication"
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));

        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // On sélectionne le filtre par date ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("par date")).perform(click());
        onView(withText("OK")).perform(click());
        //... et on vérifie que la liste contient bien une réunion avec pour objet "Communication"
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        // On sélectionne le filtre réinitialiser les filtres ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Réinitialiser les filtres")).perform(click());


        // Added a sleep statement to match the app's execution delay.
        // The recommended way to handle such scenarios is to use Espresso idling resources:
        // https://google.github.io/android-testing-support-library/docs/espresso/idling-resource/index.html
        try {
            Thread.sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // ------ SUPPRIMER LA REUNION CREEE ------

        onView(withId(R.id.meetings_list_recyclerView))
                .perform(TestUtils.actionOnItemViewAtPosition(0, R.id.item_list_delete_button, click()));

    }

}
