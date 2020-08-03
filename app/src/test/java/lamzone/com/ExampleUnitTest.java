package lamzone.com;

import org.hamcrest.collection.IsIterableContainingInAnyOrder;
import org.junit.Before;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lamzone.com.di.DI;
import lamzone.com.model.Meeting;
import lamzone.com.model.Participant;
import lamzone.com.model.Room;
import lamzone.com.service.DummyMeetingGenerator;
import lamzone.com.service.MeetingApiService;
import lamzone.com.service.ParticipantGenerator;
import lamzone.com.service.RoomGenerator;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    private MeetingApiService service;

    @Before
    public void setup() {
        service = DI.getNewInstanceApiService();
    }


    @Test
    public void getMeetingWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> expectedMeetings = DummyMeetingGenerator.DUMMY_MEETINGS;
        assertThat(meetings, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedMeetings.toArray()));
    }

    @Test
    public void getRoomWithSuccess() {
        List<Room> rooms = service.getRooms();
        List<Room> expectedRooms = RoomGenerator.DUMMY_ROOMS;
        assertThat(rooms, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedRooms.toArray()));
    }

    @Test
    public void getParticipantWithSuccess() {
        List<Participant> participants = service.getParticipants();
        List<Participant> expectedParticipants= ParticipantGenerator.DUMMY_PARTICIPANTS;
        assertThat(participants, IsIterableContainingInAnyOrder.containsInAnyOrder(expectedParticipants.toArray()));
    }




    // Supprimer un meeting
    @Test
    public void deleteMeetingWithSuccess() {
        Meeting meetingToDelete = service.getMeetings().get(0);
        service.deleteMeeting(meetingToDelete);
        assertFalse(service.getMeetings().contains(meetingToDelete));
    }



    // Ajouter un meeting
    @Test
    public void addMeetingWithSuccess() {
        Meeting meetingToAdd = new Meeting(142, "Réunion test", getDate("01/08/2020"), getDate("01/08/2020"),RoomGenerator.DUMMY_ROOMS.get(6), Collections.singletonList(ParticipantGenerator.DUMMY_PARTICIPANTS.get(6)));
        service.addMeeting(meetingToAdd);
        assertTrue(service.getMeetings().contains(meetingToAdd));
    }

    private static Date getDate(String dateStr) {
        try {
            return
                    (new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault())).parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Date();
    }



    // Filtrer les meetings par dates
/*    @Test
    public void filterByDateWithSuccess() {
        List<Meeting> meetings = service.getMeetings();
        List<Meeting> meetingByCreasingDate = DummyMeetingGenerator.DUMMY_MEETINGS.get();

    }*/
    // L'idée est de récupérer la liste des meetings, puis récupérer les dates de début et les trier et vérifier si chaque date est antérieure à la précédente


}
