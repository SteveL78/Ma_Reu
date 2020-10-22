package lamzone.com.service;


// DummyMeetingApiService -----Récupère les infos depuis ----> DummyMeetingGenerator
//|
//|----------> Fourni la liste des Meetings à la demande

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import lamzone.com.model.Meeting;
import lamzone.com.model.Participant;
import lamzone.com.model.Room;

/**
 * Dummy mock for the Api
 * Created by Steve LEROY on 04/04/2020.
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings(); // renvoie un meeting
    private List<Room> rooms = RoomGenerator.generateRooms(); // renvoie une room
    private List<Participant> participants = ParticipantGenerator.generateParticipants(); // renvoie un participant


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings;
    }

    @Override
    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public List<Participant> getParticipants() {
        return participants;
    }


    /**
     * {@inheritDoc}
     * @return
     */
    @Override
    public List<Meeting> deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);                               // supprime un meeting
        return null;
    }


    @Override
    public void addMeeting(Meeting meeting) {
        meetings.set(0, meeting);
    }    // Ajouter un meeting


    // On récupère la date sélectionnée par le DatePicker
    @Override
    public List<Meeting> filterMeetingListForDay(Calendar calendar) {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        // On le compare à notre liste
        List<Meeting> result = new ArrayList<Meeting>();
        for (Meeting m : meetings) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(m.getStartTime());
            int meetingYear = cal.get(Calendar.YEAR);
            int meetingMonth = cal.get(Calendar.MONTH);
            int meetingDayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

            if (meetingYear == year && meetingMonth == month && meetingDayOfMonth == dayOfMonth) {
                result.add(m);
            }
        }
        return result;
    }


    @Override
    public List<Meeting> filterMeetingListForRoom(String room) {
        // Récupérer la liste de réunions par salle
        List<Meeting> result = new ArrayList<>();
        for (Meeting m : meetings) {
            String nameRoom = m.getRoom().getName();

            if (nameRoom.equals(room)) {
                result.add(m);
            }
        }

        // l'afficher
        return result;
    }


}