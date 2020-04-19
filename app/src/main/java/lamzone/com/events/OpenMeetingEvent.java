package lamzone.com.events;

import lamzone.com.model.Meeting;

/**
 * Created by Steve LEROY on 18/04/2020.
 */
public class OpenMeetingEvent {


    /**
     * Meeting to open
     */
    public Meeting mMeeting;


    /**
     * Constructor
     * @param meeting
     */
    public OpenMeetingEvent(Meeting meeting) {this.mMeeting = meeting; }
}
