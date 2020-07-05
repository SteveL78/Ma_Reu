package lamzone.com.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import lamzone.com.R;
import lamzone.com.controller.MainActivity;

/**
 * Created by Steve LEROY on 05/07/2020.
 */
/*public class RoomFilterList extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Filtre par salle")
                .setItems(R.array.rooms, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                ((MainActivity) getActivity()).filterList("salle Mario");
                                return;
                            case 2:
                                ((MainActivity) getActivity()).filterList("salle Luigi");
                                return;
                            case 3:
                                ((MainActivity) getActivity()).filterList("salle Peach");
                                return;
                            case 4:
                                ((MainActivity) getActivity()).filterList("salle Toad");
                                return;
                            case 5:
                                ((MainActivity) getActivity()).filterList("salle Yoshi");
                                return;
                            case 6:
                                ((MainActivity) getActivity()).filterList("salle Harmonie");
                                return;
                            case 7:
                                ((MainActivity) getActivity()).filterList("salle Wario");
                                return;
                            case 8:
                                ((MainActivity) getActivity()).filterList("salle Géno");
                                return;
                            case 9:
                                ((MainActivity) getActivity()).filterList("salle Pauline");
                                return;
                            case 10:
                                ((MainActivity) getActivity()).filterList("salle Fonky Kong");
                                return;
                            default:
                                ((MainActivity) getActivity()).filterList("");
                                return;
                        }
                    }
                });
        return builder.create();
    }
}*/
