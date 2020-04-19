package lamzone.com.service;

import java.util.List;

import lamzone.com.model.Meeting;

/**
 * Neighbour API client
 * Created by Steve LEROY on 04/04/2020.
 */

public interface MeetingApiService {

    /**
     * Get all my Meetings
     * @return {@link List}
     */
    List<Meeting> getMeetings();   // renvoie meeting

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);  // supprime le meeting


    /**
     * Add meeting
     */
    //void addMeeting(Meeting meeting);     // ajoute un meeting





}