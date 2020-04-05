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
    List<Meeting> getMeeting();   // renvoie meeting

    /**
     * Deletes a meeting
     * @param meeting
     */
    void deleteMeeting(Meeting meeting);  // supprime le meeting


    /**
     * Add neighbour
     */
    void addMeeting(Meeting meeting);     // ajoute un meeting


    /**
     * Add to favorite
     */
    void addFavorite(Meeting meeting);     // ajoute un meeting aux favoris


    /**
     * Remove for favorite
     */
    void removeFavorite(Meeting meeting);          // supprime un meeting des favoris


    List<Meeting> getFavoriteMeetings();        // renvoie la liste de meetings favoris

}