package lamzone.com.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import lamzone.com.R;
import lamzone.com.model.Room;

/**
 * Created by Steve LEROY on 04/04/2020.
 */
public class RoomGenerator {

    //Création de la liste des salles avec les informations devant s'afficher pour chacune
    public static List<Room> DUMMY_ROOM = Arrays.asList(
            new Room(1, "Mario", "Verte"),
            new Room(2, "Luigi", "Bleu"),
            new Room(3, "Peach", "Rouge"),
            new Room(4, "Toad", "Jaune"),
            new Room(5, "Yoshi", "Violet"),
            new Room(6, "Tiara", "Orange"),
            new Room(7, "Wario", "Rose"),
            new Room(8, "Geno", "Saumon"),
            new Room(9, "Pauline", "Blanc"),
            new Room(10, "Funky Kong", "Noir")
    );


    // Génère une liste à partir des salles créées
    public static List<Room> generateRooms() { return new ArrayList<>(DUMMY_ROOM); }

}
