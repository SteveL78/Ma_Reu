package lamzone.com.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lamzone.com.model.Meeting;
import lamzone.com.model.Participant;
import lamzone.com.model.Room;


/**
 * Created by Steve LEROY on 04/04/2020.
 */
public abstract class DummyMeetingGenerator {

        // Création de la liste des voisins avec informations devant s'afficher dans sa fiche
        private static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
                new Meeting(1, "Réunion A", getDate("10/05/2020 12:55"), getDate("10/05/2020 13:55"),  RoomGenerator.DUMMY_ROOMS.get(0),ParticipantGenerator.DUMMY_PARTICIPANTS),
                new Meeting(2, "Réunion B", getDate("10/05/2020 08:00"), getDate("10/05/2020 08:30"), RoomGenerator.DUMMY_ROOMS.get(0), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(0))),
                new Meeting(3, "Réunion C", getDate("10/05/2020 16:00"), getDate("10/05/2020 16:30"),  RoomGenerator.DUMMY_ROOMS.get(2), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(2))),
                new Meeting(4, "Réunion B", getDate("10/05/2020 09:00"), getDate("10/05/2020 09:45"),  RoomGenerator.DUMMY_ROOMS.get(1), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(1))),
                new Meeting(5, "Réunion D", getDate("10/05/2020 13:00"), getDate("10/05/2020 13:55"),  RoomGenerator.DUMMY_ROOMS.get(3), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(3))),
                new Meeting(6, "Réunion A", getDate("10/05/2020 10:00"), getDate("10/05/2020 10:45"),  RoomGenerator.DUMMY_ROOMS.get(5), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(4))),
                new Meeting(7, "Réunion A", getDate("10/05/2020 15:00"), getDate("10/05/2020 15:37"),  RoomGenerator.DUMMY_ROOMS.get(4), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(5))),
                new Meeting(8, "Réunion E", getDate("10/05/2020 11:00"), getDate("10/05/2020 11:45"),  RoomGenerator.DUMMY_ROOMS.get(6), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(7))),
                new Meeting(9, "Réunion C", getDate("10/05/2020 18:00"), getDate("10/05/2020 18:45"),  RoomGenerator.DUMMY_ROOMS.get(8), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(6))),
                new Meeting(10, "Réunion D", getDate("10/05/2020 17:00"),getDate("10/05/2020 17:45"),  RoomGenerator.DUMMY_ROOMS.get(7), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(1))),
                new Meeting(11, "Réunion E", getDate("10/05/2020 12:00"), getDate("10/05/2020 12:45"),  RoomGenerator.DUMMY_ROOMS.get(8), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(3))),
                new Meeting(12, "Réunion C", getDate("10/05/2020 14:00"), getDate("10/05/2020 14:45"),  RoomGenerator.DUMMY_ROOMS.get(9), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(4)))
        );

public static Date getDate(String dateStr){
    try {
        return
        (new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())).parse(dateStr);
    } catch (ParseException e) {
        e.printStackTrace();
    }
    return new Date();
}

        // Génère une liste à partir des voisins créés
        static List<Meeting> generateMeetings() {
            return new ArrayList<>(DUMMY_MEETINGS);
        }
    }