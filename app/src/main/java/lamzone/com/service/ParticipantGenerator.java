package lamzone.com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lamzone.com.model.Participant;
import lamzone.com.model.Room;

/**
 * Created by Steve LEROY on 12/04/2020.
 */
public class ParticipantGenerator {


    //Création de la liste des salles avec les informations devant s'afficher pour chacune
    public static List<Participant> DUMMY_PARTICIPANTS = Arrays.asList(
            new Participant(1, "Charles", "charles@lamzone.com"),
            new Participant(2, "Steve", "steve@lamzone.com"),
            new Participant(3, "Latif", "latif@lamzone.com"),
            new Participant(4, "Jabbar", "jabbar@lamzone.com"),
            new Participant(5, "Sophie", "sophie@lamzone.com"),
            new Participant(6, "Timothé", "timothe@lamzone.com"),
            new Participant(7, "Mathias", "mathias@lamzone.com"),
            new Participant(8, "Cassandra", "cassandra@lamzone.com")
    );


    // Génère une liste à partir des salles créées
    static List<Participant> generateParticipants() { return new ArrayList<>(DUMMY_PARTICIPANTS); }

}

