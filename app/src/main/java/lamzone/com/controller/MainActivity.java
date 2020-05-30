package lamzone.com.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import lamzone.com.R;
import lamzone.com.di.DI;
import lamzone.com.events.DeleteMeetingEvent;
import lamzone.com.events.OpenMeetingEvent;
import lamzone.com.service.MeetingApiService;
import lamzone.com.ui.MyMeetingRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {


    private MeetingApiService mApiService;
    private MyMeetingRecyclerViewAdapter adapter;
    private RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mApiService = DI.getMeetingApiService();
        adapter = new MyMeetingRecyclerViewAdapter(mApiService.getMeetings());
        rv = findViewById(R.id.meetings_list_recyclerView);

        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        FloatingActionButton fabBtn = findViewById(R.id.fab_btn);

        fabBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, CreateMeetingActivity.class);
                startActivity(intent);

            }
        });

    }


    /**
     * Fired if the user clicks on meeting
     *
     * @param event
     */
    @Subscribe
    public void openMeeting(OpenMeetingEvent event) {
        // On ouvre une nouvelle activité (=CreateMeetingActivity) quand on clique sur un meeting de la liste
        Intent intent = new Intent(this, CreateMeetingActivity.class);
        intent.putExtra("meeting", event.meeting);
        startActivity(intent);
    }


    /**
     * Fired if the user clicks on delete button
     */

    @Subscribe
    public void deleteMeeting(DeleteMeetingEvent deleteMeetingEvent) {
        mApiService.deleteMeeting(deleteMeetingEvent.getMeeting());
        adapter.notifyDataSetChanged();
    }


    // ==================== TOOLBAR ====================
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        MenuItem menuItem = menu.findItem(R.id.filter_icon);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.all_meetings:
                Toast.makeText(this, "Menu Toutes les réunions sélectionné", Toast.LENGTH_LONG).show();
                return true;


            case R.id.select_menu:
                Toast.makeText(this, "Menu Trier sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_room:
                Toast.makeText(this, "Menu Trier par salle sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_ascending_date:
                Toast.makeText(this, "Menu Trier par date croissant sélectionné", Toast.LENGTH_LONG).show();
                return true;
            case R.id.select_by_increasing_date:
                Toast.makeText(this, "Menu Trier par date décroissant sélectionné", Toast.LENGTH_LONG).show();
                return true;


            case R.id.filter_menu:
                Toast.makeText(this, "Menu Filtrer sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_date:
                Toast.makeText(this, "Menu Filtrer par date sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.filter_by_room:
                Toast.makeText(this, "Menu Filtrer par salle sélectionné", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.Mario:
                Toast.makeText(this, "Salle Mario sélectionnée", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);

        }
    } // ==================== FIN DE LA TOOLBAR ====================


    // On s'enregistre auprès d'eventBus pour recevoir un évènement (onStart et onStop car cycle de vie de l'activité ou du fragment)
    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }


}