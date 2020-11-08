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
import static org.hamcrest.core.IsNot.not;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);


    @Test
    public void createMeeting() {

        // On crée le nouveau meeting ("Communication")
        createNewMeeting();

        // ... et on vérifie que le meeting apparaît en position 0 ...
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));

        // ... et on le supprime.
        deleteNewMeeting();
    }

    @Test
    public void filterByDate() {

        // On crée le nouveau meeting ("Communication")
        createNewMeeting();

        // On sélectionne le filtre par date ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText(R.string.main_menu_filter_by_date)).perform(click());
        onView(withText(android.R.string.ok)).perform(click());

        //... et on vérifie que la liste contient bien une réunion avec pour objet "Communication"
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));

        // ... et on le supprime.
        deleteNewMeeting();
    }

    @Test
    public void filterByRoom() {

        // On crée le nouveau meeting ("Communication")
        createNewMeeting();

        // On sélectionne le filtre par salle ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("par salle")).perform(click());
        onView(withText("Funky Kong")).perform(click());
        onView(withText(android.R.string.ok)).perform(click());

        //... et on vérifie que la liste contient bien une réunion avec pour objet "Communication" ...
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));

        // ... et on le supprime.
        deleteNewMeeting();
    }


    @Test
    public void resetFilter() {

        // On sélectionne seulement les réunions dans la salle "Funky Kong"
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("par salle")).perform(click());
        onView(withText("Funky Kong")).perform(click());
        onView(withText(android.R.string.ok)).perform(click());

        // On sélectionne dans le menu filtre "Réinitialiser les filtres" ...
        Espresso.openContextualActionModeOverflowMenu();
        onView(withText("Réinitialiser les filtres")).perform(click());

        // ... et on vérifie que c'est bien la "Réunion A" qui apparaît à la position 0.
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Réunion A")));
    }


    @Test
    public void deleteMeeting() {

        // On vérifie que la 1ère réunion est bien "Réunion B" (car réunion A suppriméé dans le filtre donc plus présente en 1ère position)...
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Réunion B")));

        //... puis on clique sur son bouton delete ...
        onView(withId(R.id.meetings_list_recyclerView))
                .perform(TestUtils.actionOnItemViewAtPosition(0, R.id.item_list_delete_button, click()));

        //... et on vérifie que la "Réunion A" n'apparaît plus en position 0
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(not(withText("Réunion B"))));
    }


    private void deleteNewMeeting() {
        onView(withRecyclerView(R.id.meetings_list_recyclerView).atPositionOnView(0, R.id.item_list_object))
                .check(matches(withText("Communication")));
        onView(withId(R.id.meetings_list_recyclerView))
                .perform(TestUtils.actionOnItemViewAtPosition(0, R.id.item_list_delete_button, click()));

    }


    private void createNewMeeting() {
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


        // On clique sur "Enregistrer" pour finaliser la création de la réunion
        onView(withId(R.id.save_btn)).perform(click());


    }


}
