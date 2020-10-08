package lamzone.com.model;

import java.io.Serializable;

/**
 * Model object representing a Room
 * Created by Steve LEROY on 04/04/2020.
 */
public class Room implements Serializable {

    private int id;
    private String nameRoom;
    private int colorRoom;

    public Room(int id, String nameRoom, int colorRoom) {
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

    public String getName() {
        return nameRoom;
    }

    public int getColor() {
        return colorRoom;
    }


}
