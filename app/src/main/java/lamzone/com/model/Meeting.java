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
    private String object;      // objet de la réunion (=réunion A, réunion B, réunion C ...)
    private String startTime;   // heure de début de la réunion
    private String endTime;     // heure de fin de la réunion
    private String startDate;   // date de début de la réunion

    private Room room;  // n° de la salle
    private List<Participant> participants; // email des particpants

    private boolean roomIsFree;     // salle libre ?


    /**
     * Constructor
     *
     * @param id
     * @param object
     * @param room
     */
    public Meeting(int id, String object, String startTime, String endTime, String startDate, Room room, List<Participant> participants) {
        this.id = id;
        this.object = object;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startDate = startDate;

        this.room = room;
        this.participants = participants;

        this.roomIsFree = true;
    }


    public boolean isRoomIsFree() { return roomIsFree; }

    public void setFree(boolean free) {
        this.roomIsFree = free;
    }

    public int getId() {
        return id;
    }

    public void setId(String id) { this.id = id; }

    public String getName() {
        return object;
    }

    public void setObject(String name) {
        this.object = object;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getRoomName() {
        return room;
    }

    public String getParticipants() {
        return Participant;
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
