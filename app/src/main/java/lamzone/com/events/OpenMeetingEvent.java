package lamzone.com.events;

import lamzone.com.model.Meeting;

/**
 * Created by Steve LEROY on 18/04/2020.
 * Event fired when a user open a Meeting
 * On signale donc qu'on ouvre un nouveau Meeting
 */


public class OpenMeetingEvent {

    /**
     * Meeting to open
     */
    public Meeting meeting;


    /**
     * Constructor
     *
     * @param meeting
     */
    public OpenMeetingEvent(Meeting meeting) {
        this.meeting = meeting;
    }

}
