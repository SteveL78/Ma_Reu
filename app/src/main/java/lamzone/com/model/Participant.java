package lamzone.com.model;

import java.io.Serializable;

/**
 * Created by Steve LEROY on 04/04/2020.
 */
public class Participant implements Serializable {

    private int id;
    private String participantName;
    private String eMail;

    public Participant(int id, String participantName, String eMail) {
        this.id = id;
        this.participantName = participantName;
        this.eMail = eMail;
    }


    /**
     * Constructor
     */

    public int getId() {
        return id;
    }

    public String getParticipantName() {
        return participantName;
    }

    public String geteMail() {
        return eMail;
    }


}
