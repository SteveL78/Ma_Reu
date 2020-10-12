package lamzone.com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lamzone.com.model.Participant;

/**
 * Created by Steve LEROY on 12/04/2020.
 */
public class ParticipantGenerator {


    //Création de la liste des participants avec les informations devant s'afficher pour chacuns
    public static List<Participant> DUMMY_PARTICIPANTS = Arrays.asList(
            new Participant(41, "Charles", "charles@lamzone.com"),
            new Participant(213, "Steve", "steve@lamzone.com"),
            new Participant(33, "Latif", "latif@lamzone.com"),
            new Participant(489, "Jabbar", "jabbar@lamzone.com"),
            new Participant(529, "Sophie", "sophie@lamzone.com"),
            new Participant(68, "Timothé", "timothe@lamzone.com"),
            new Participant(715, "Mathias", "mathias@lamzone.com"),
            new Participant(186, "Cassandra", "cassandra@lamzone.com")
    );


    // Génère une liste à partir des participants créés
    static List<Participant> generateParticipants() {
        return new ArrayList<>(DUMMY_PARTICIPANTS);
    }

}

