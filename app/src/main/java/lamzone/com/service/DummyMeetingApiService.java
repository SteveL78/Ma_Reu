package lamzone.com.service;


// DummyMeetingApiService -----Récupère les infos depuis ----> DummyMeetingGenerator
//|
//|----------> Fourni la liste des Meetings à la demande

import java.util.ArrayList;
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
    public List<Room> getRooms() {return rooms;}
    @Override
    public List<Participant> getParticipants() {return participants;}


    /**
     * {@inheritDoc}
     */
    @Override
    public void deleteMeeting(Meeting meeting) {
        meetings.remove(meeting);                               // supprime un meeting
    }




    @Override
    public void addMeeting (Meeting meeting) {
        meetings.add(meeting);
        }    // Ajouter un meeting



}