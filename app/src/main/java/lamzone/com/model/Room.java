package lamzone.com.model;

import java.io.Serializable;

/**
 * Model object representing a Room
 * Created by Steve LEROY on 04/04/2020.
 */
public class Room implements Serializable {

    private int id;
    private String nameRoom;
    private String colorRoom;

    public Room(int id, String nameRoom, String colorRoom) {
        this.id = id;
        this.nameRoom = nameRoom;
        this.colorRoom = colorRoom;
    }


    /**
     * Constructor
     */

    public int getId() {
        return id;
    }

    public String getNameRoom() {
        return nameRoom;
    }

    public String getColorRoom() {
        return colorRoom;
    }


}
