package lamzone.com.ui;

import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lamzone.com.R;
import lamzone.com.events.DeleteMeetingEvent;
import lamzone.com.events.OpenMeetingEvent;
import lamzone.com.model.Meeting;

/**
 * Created by Steve LEROY on 07/04/2020.
 */
public class MyMeetingRecyclerViewAdapter extends RecyclerView.Adapter<MyMeetingRecyclerViewAdapter.ViewHolder> {

    // POUR LES DONNEES
    private List<Meeting> mMeetings;


    // CONSTRUCTOR
    public MyMeetingRecyclerViewAdapter(List<Meeting> items) {
        mMeetings = items;
    }




    /* OnCreateViewHolder permet de créer un ViewHolder à partir du layout xml représentant chaque ligne de la RecyclerView
     *
     * Elle sera appelée pour les 1ères lignes visibles de la RecyclerView */

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_meeting, parent, false);
        return new ViewHolder(view);
    }


    /* OnBindViewHolder est une méthode appelée pour chacune des lignes visibles dans notre RecyclerView
     * On y met à jour leur apparence (to bind = lier)*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        Meeting meeting = mMeetings.get(position);            // Dans la liste des réunions on récupère une réunion à la position qui est donnée
        String dateLabel = DateFormat.format(" - hh:mm - ", meeting.getStartTime()).toString();
        dateLabel = dateLabel.replace(':', 'h');
        holder.mMeetingObject.setText(meeting.getSubject());     // on affiche l'objet (le topic) de la réunion
        holder.mBeginHour.setText(dateLabel);         // On affiche l'heure de début de la réunion
        holder.mRoomName.setText(meeting.getRoom().getName());          // on affiche le nom de la salle de réunion
        holder.mColorRoom.setImageResource(meeting.getRoom().getColor());
        holder.mEMail.setText(meeting.getParticipants().get(0).getEMail());

        // TODO afficher la liste des email




        /* ======================= BOUTON DELETE =======================
         * Quand on clique sur le bouton delete on diffuse un évènement précisant la suppression
         */
        holder.mDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.mDeleteButton.getContext(), "Réunion supprimée", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new DeleteMeetingEvent(meeting));

            }
        });

        // Quand je clique sur toute la vue voilà ce qu'il se passe
        holder.mParentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(holder.mDeleteButton.getContext(), "click", Toast.LENGTH_SHORT).show();
                EventBus.getDefault().post(new OpenMeetingEvent(meeting)); // ouvre le meeting
            }
        });
    }

    // ============= RECYCLERVIEW : ON R2CUPERE LE NOMBRE D'ELEMENTS =============
    @Override
    public int getItemCount() {
        return mMeetings.size();
    }


    // On relie les informations à afficher
    public class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_list_color)    // couleur de la salle
        public ImageView mColorRoom;

        @BindView(R.id.item_list_object)   // l'objet de la réunion (Réunion A, B, ...)
        public TextView mMeetingObject;

        @BindView(R.id.item_list_hour)     // l'heure de la réunion
        public TextView mBeginHour;

        @BindView(R.id.item_list_room_name)    // le nom de la salle
        public TextView mRoomName;

        @BindView(R.id.item_list_delete_button)     // le bouton delete
        public ImageButton mDeleteButton;

        @BindView(R.id.email)     // l'eMail du participant'
        public TextView mEMail;

        @BindView(R.id.item_list_parent)
        public ConstraintLayout mParentView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
