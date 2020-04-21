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
    public static List<Room> DUMMY_ROOMS = Arrays.asList(
            new Room(1561, "Mario", R.drawable.ic_brightness_1_blue2_24dp),
            new Room(2582, "Luigi", R.drawable.ic_brightness_1_blue_24dp),
            new Room(35, "Peach", R.drawable.ic_brightness_1_green2_24dp),
            new Room(419, "Toad", R.drawable.ic_brightness_1_green_24dp),
            new Room(5525, "Yoshi", R.drawable.ic_brightness_1_lilas_24dp),
            new Room(2526, "Harmonie", R.drawable.ic_brightness_1_peach1_24dp),
            new Room(735, "Wario", R.drawable.ic_brightness_1_pink2_24dp),
            new Room(758, "Geno", R.drawable.ic_brightness_1_pink_24dp),
            new Room(369, "Pauline", R.drawable.ic_brightness_1_violet_24dp),
            new Room(160, "Funky Kong", R.drawable.ic_brightness_1_yellow_24dp)
    );


    // Génère une liste à partir des salles créées
    public static List<Room> generateRooms() { return new ArrayList<>(DUMMY_ROOMS); }

}
