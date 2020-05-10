package lamzone.com.model;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

/**
 * Model object representing a Meeting
 * Created by Steve LEROY on 04/04/2020.
 */
public class Meeting implements Serializable {

    /**
     * Identifier
     * Tout ce qui définit une Réunion
     */
    private int id;             // position dans la liste
    private String topic;      // thème de la réunion (=réunion A, réunion B, réunion C ...)
    private String startTime;   // heure de début de la réunion //TODO put Date
    private String endTime;     // heure de fin de la réunion   //TODO put Date
    private String startDate;   // date de début de la réunion //TODO remove

    private Room room;          // n° de la salle
    private List<Participant> participants; // email des particpants


    /**
     * Constructor
     *
     * @param id
     * @param object
     * @param room
     */
    public Meeting (int id, String object, String startTime, String endTime, String startDate, Room room, List<Participant> participants) {
        this.id = id;
        this.topic = object;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;

        this.room = room;
        this.participants = participants;

    }

    public int getId() { return id;}

    public void setId(int id) { this.id = id;}



    public String getTopic() { return topic;}

    public void setTopic(String topic) { this.topic = topic;}




    public void setStartTime (String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getStartTime() {
        return startTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meeting meeting = (Meeting) o;
        return Objects.equals(id, meeting.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
