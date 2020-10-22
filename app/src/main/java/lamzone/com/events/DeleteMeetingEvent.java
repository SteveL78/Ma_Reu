package lamzone.com.events;

import lamzone.com.model.Meeting;

/**
 * Event fired when a user deletes a Meeting
 * Created by Steve LEROY on 04/04/2020.
 */
public class DeleteMeetingEvent {

    /**
     * Meeting to delete
     */
    public Meeting meeting;


    /**
     * Constructor.
     *
     * @param meeting
     */
    public DeleteMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }

   public Meeting getMeeting() {
        return meeting;
    }
}