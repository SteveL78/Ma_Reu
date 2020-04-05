package lamzone.com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lamzone.com.model.Meeting;


/**
 * Created by Steve LEROY on 04/04/2020.
 */
public abstract class DummyMeetingGenerator {

        // Création de la liste des voisins avec informations devant s'afficher dans sa fiche
        public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
                new Meeting(1, "Réunion A", "12h00", "12h45", "12/09/2020", "12/09/2020","Peach"),
                new Meeting(2, "Réunion B", "08h00", "08h30", "12/09/2020", "12/09/2020","Mario"),
                new Meeting (3, "Réunion C", "16h00", "16h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(4, "Réunion B", "09h00", "09h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(5, "Réunion D", "13h00", "13h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(6, "Réunion A", "10h00", "10h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(7, "Réunion A", "15h00", "15h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(8, "Réunion E", "11h00", "11h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(9, "Réunion C", "18h00", "18h45", "12/09/2020", "12/09/2020","xx"),
                new Meeting(10, "Réunion D", "17h00", "17h45", "12/09/2020", "12/09/2020","Toad"),
                new Meeting(11, "Réunion E", "12h00", "12h45", "12/09/2020", "12/09/2020","Luigi"),
                new Meeting(12, "Réunion C", "14h00", "45", "12/09/2020", "12/09/2020","www.facebook.fr/ludovic")
        );

        // Génère une liste à partir des voisins créés
        static List<Meeting> generateMeetings() {
            return new ArrayList<>(DUMMY_MEETINGS);
        }
    }