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


/**
 * Created by Steve LEROY on 04/04/2020.
 */
public abstract class DummyMeetingGenerator {

    // Création de la liste des réunions avec informations
    public static List<Meeting> DUMMY_MEETINGS = Arrays.asList(
            new Meeting(791, "Réunion A", getDate("15/01/2020 12:55"), getDate("15/01/2020 13:55"), RoomGenerator.DUMMY_ROOMS.get(0), ParticipantGenerator.DUMMY_PARTICIPANTS),
            new Meeting(226, "Réunion B", getDate("15/01/2020 08:00"), getDate("15/01/2020 08:30"), RoomGenerator.DUMMY_ROOMS.get(0), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(0))),
            new Meeting(453, "Réunion C", getDate("27/01/2020 16:00"), getDate("27/01/2020 16:30"), RoomGenerator.DUMMY_ROOMS.get(2), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(2))),
            new Meeting(45, "Réunion B", getDate("27/01/2020 09:00"), getDate("27/01/2020 09:45"), RoomGenerator.DUMMY_ROOMS.get(1), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(1))),
            new Meeting(511, "Réunion D", getDate("24/03/2020 13:00"), getDate("24/03/2020 13:55"), RoomGenerator.DUMMY_ROOMS.get(3), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(3))),
            new Meeting(62, "Réunion A", getDate("24/03/2020 10:00"), getDate("24/03/2020 10:45"), RoomGenerator.DUMMY_ROOMS.get(5), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(4))),
            new Meeting(97, "Réunion A", getDate("22/05/2020 15:00"), getDate("22/05/2020 15:37"), RoomGenerator.DUMMY_ROOMS.get(4), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(5))),
            new Meeting(898, "Réunion E", getDate("13/08/2020 11:00"), getDate("13/08/2020 11:45"), RoomGenerator.DUMMY_ROOMS.get(6), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(7))),
            new Meeting(94, "Réunion C", getDate("12/09/2020 18:00"), getDate("13/08/2020 18:45"), RoomGenerator.DUMMY_ROOMS.get(8), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(6))),
            new Meeting(100, "Réunion D", getDate("12/09/2020 17:00"), getDate("12/092020 17:45"), RoomGenerator.DUMMY_ROOMS.get(7), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(1))),
            new Meeting(625, "Réunion E", getDate("24/12/2020 12:00"), getDate("24/12/2020 12:45"), RoomGenerator.DUMMY_ROOMS.get(8), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(3))),
            new Meeting(1200, "Réunion C", getDate("31/12/2020 14:00"), getDate("31/12/2020 14:45"), RoomGenerator.DUMMY_ROOMS.get(9), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(4)))
    );

    private static Date getDate(String dateStr) {
        try {
            return
                    (new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }

    // Génère une liste à partir des réunions créées
    static List<Meeting> generateMeetings() {
        return new ArrayList<>(DUMMY_MEETINGS);
    }
}