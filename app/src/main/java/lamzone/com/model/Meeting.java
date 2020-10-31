package lamzone.com.model;
import java.io.Serializable;
import java.util.Date;
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
    private long id;             // position dans la liste
    private String subject;      // sujet de la réunion (=réunion A, réunion B, réunion C ...)
    private Date startTime;   // heure de début de la réunion
    private Date endTime;     // heure de fin de la réunion

    private Room room;          // n° de la salle
    private List<Participant> participants; // email des particpants


    /**
     * Constructor
     *
     * @param id
     * @param subject
     * @param room
     */
    public Meeting(long id, String subject, Date startTime, Date endTime, Room room, List<Participant> participants) {
        this.id = id;
        this.subject = subject;
        this.startTime = startTime;
        this.endTime = endTime;

        this.room = room;
        this.participants = participants;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getSubject() {
        return subject;
    }


    public Date getStartTime() {
        return startTime;
    }


    public Date getEndTime() {
        return endTime;
    }


    public Room getRoom() {
        return room;
    }


    public List<Participant> getParticipants() {
        return participants;
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