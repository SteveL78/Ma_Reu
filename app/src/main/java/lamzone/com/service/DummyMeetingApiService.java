package lamzone.com.service;


// DummyMeetingApiService -----Récupère les infos depuis ----> DummyMeetingGenerator
//|
//|----------> Fourni la liste des Meetings à la demande

import java.util.List;

import lamzone.com.model.Meeting;
import lamzone.com.model.Participants;
import lamzone.com.model.Room;

/**
 * Dummy mock for the Api
 * Created by Steve LEROY on 04/04/2020.
 */
public class DummyMeetingApiService implements MeetingApiService {

    private List<Meeting> meetings = DummyMeetingGenerator.generateMeetings(); // renvoie un meeting
/*
    private List<Room> room = RoomGenerator.generateRooms(); // renvoie une room
    private List<Participants> mParticipants = DummyParticipantsGenerator.generateParticipants(); // renvoie un participant
*/


    /**
     * {@inheritDoc}
     */
    @Override
    public List<Meeting> getMeetings() {
        return meetings();
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);                               // supprime un meeting


        // Mets à jour le meeting
        public void updateMeeting (Meeting meeting){
            for (int i = 0; i < meetings.size(); i++) {           // On fait le tour de la liste
                Meeting meetingTemp = meeting.getId(i);    // On fait défiler la liste tant qu'on obtient pas le meeting attendu
                if (meeting.equals(meetingTemp)) {          // si le meeting correspond à celui attendu alors on l'affiche
                    meetings.set(i, meeting);
                    break;
                }
            }
        }


        @Override
        public void addMeeting (Meeting meeting){
            meetings.add(meeting);
        }    // Ajouter un meeting


    }
