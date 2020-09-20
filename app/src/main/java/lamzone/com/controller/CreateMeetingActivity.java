package lamzone.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import lamzone.com.R;
import lamzone.com.di.DI;
import lamzone.com.model.Meeting;
import lamzone.com.model.Participant;
import lamzone.com.model.Room;
import lamzone.com.service.MeetingApiService;

public class CreateMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private MeetingApiService mApiService = DI.getMeetingApiService();


    private EditText mMeetingSubjectTv;

    private Button mStartDateBtn;

    private Button mEndDateBtn;

    private Button buttonRoom;

    private MultiAutoCompleteTextView multiAutoCompleteTextView;

    private Calendar calendarStart = null;

    private Calendar calendarEnd = null;

    private Room room = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        mMeetingSubjectTv = findViewById(R.id.meeting_subject_editText);
        mStartDateBtn = findViewById(R.id.select_date_btn);
        mEndDateBtn = findViewById(R.id.select_end_btn);
        buttonRoom = findViewById(R.id.room_btn);
        Button saveBtn = findViewById(R.id.save_btn);

        multiAutoCompleteTextView = findViewById(R.id.multiautocompletetextview);

        saveBtn.setOnClickListener(view -> onCreateMeetingClicked());

        mApiService = DI.getMeetingApiService();
        mApiService.getRooms();
        mApiService.getParticipants();


        // ============== DATETIME PICKER =================

        // DATETIMEPICKER (DEBUT DE LA REUNION)
        mStartDateBtn.setOnClickListener(view -> showDateTimeDialog(mStartDateBtn));


        // ============== TIME PICKER =================

        // DEBUT DU TIMEPICKER
        mEndDateBtn.setOnClickListener(view -> showTimeDialogEnd(mEndDateBtn));


        // =========== MULTIAUTOCOMPLETETEXTVIEW PARTICIPANT ================

        List<String> participants = new ArrayList<>();
        for (Participant participant : mApiService.getParticipants()) {
            participants.add(participant.getName());
        }
        ArrayAdapter<String> arrayAdapterParticipant;
        arrayAdapterParticipant = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, participants);
        multiAutoCompleteTextView.setAdapter(arrayAdapterParticipant);
        multiAutoCompleteTextView.setThreshold(1); // minimum number of characters
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        // ============== DATETIME PICKER =================

        // DATETIMEPICKER (DEBUT DE LA REUNION)
        mStartDateBtn.setOnClickListener(view -> showDateTimeDialog(mStartDateBtn));


        // ============== TIME PICKER =================

        // DEBUT DU TIMEPICKER
        mEndDateBtn.setOnClickListener(view -> showTimeDialogEnd(mEndDateBtn));
    }


    // =========== SUITE DATETIMEPICKER ================

    private void showDateTimeDialog(final Button mStartDateBtn) {          // SUITE DATETIMEPICKER (DEBUT DE LA REUNION)
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, dayOfMonth) -> {
            calendarStart = Calendar.getInstance();
            calendarStart.set(Calendar.YEAR, year);
            calendarStart.set(Calendar.MONTH, month);
            calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) -> {
                calendarStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarStart.set(Calendar.MINUTE, minute);

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
                mStartDateBtn.setText(simpleDateFormat.format(calendarStart.getTimeInMillis()));
            };

            new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendarStart.get(Calendar.HOUR_OF_DAY), calendarStart.get(Calendar.MINUTE), true).show();
        };

        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog dp = new DatePickerDialog(CreateMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Date date = new Date();
        dp.getDatePicker().setMinDate(date.getTime());
        dp.show();
    }        // FIN DATETIMEPICKER (DEBUT DE LA REUNION)


    // =========== SUITE TIMEPICKER ================

    private void showTimeDialogEnd(final Button mEndDateBtn) {             // SUITE TIMEPICKER

        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hourOfDay, minute) -> {
            calendarEnd = Calendar.getInstance();
            calendarEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendarEnd.set(Calendar.MINUTE, minute);
            calendarEnd.set(Calendar.DAY_OF_MONTH, calendarStart.get(Calendar.DAY_OF_MONTH));
            calendarEnd.set(Calendar.MONTH, calendarStart.get(Calendar.MONTH));
            calendarEnd.set(Calendar.YEAR, calendarStart.get(Calendar.YEAR));

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
            mEndDateBtn.setText(simpleDateFormat.format(calendarEnd.getTimeInMillis()));
        };

        final Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }       // FIN TIMEPICKER


    // ============= VERIFICATION DES CHAMPS LORS DE L'ENREGISTREMENT  =============

    private void onCreateMeetingClicked() {

        String meetingSubject = mMeetingSubjectTv.getText().toString();
        String multiParticipants = multiAutoCompleteTextView.getText().toString();


        // On vérifie que l'utilisateur a bien indiqué un sujet de réunion
        if (meetingSubject.isEmpty()) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Merci d'indiquer le sujet de la réunion.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On vérifie que l'utilisateur a bien indiqué une date et une heure de début de réunion
        if (calendarStart == null) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Veillez à bien indiquer une date et une heure de début.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On vérifie que l'utilisateur a bien indiqué une heure de fin de réunion
        if (calendarEnd == null) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Merci de préciser l'heure de fin de la réunion.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }

        Date startDate = calendarStart.getTime();
        Date endDate = calendarEnd.getTime();


        // On vérifie que la date de fin n'est pas antérieure à la date de début
        if (startDate.after(endDate)) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Merci de modifier l'heure de fin de réunion qui ne peut être antérieure à l'heure de début.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On vérifie que l'utilisateur a bien sélectionné une salle de réunion
        if (room == null) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Vous devez sélectionner une salle de réunion.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On vérifie qu'au moins 1 participant est indiqué
        ArrayList<String> participantNameList = new ArrayList<>();
        String[] unfilteredParticipants = multiAutoCompleteTextView.getText().toString().split(",");

        for (String p : unfilteredParticipants) {
            String str = p.trim();
            if (!str.isEmpty()) {
                participantNameList.add(str);
            }
        }
        if (participantNameList.isEmpty()) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Merci d'indiquer au moins un participant.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On vérifie si la salle est libre
        boolean roomIsFree = true;
        for (Meeting m : mApiService.getMeetings()) {
            if (m.getRoom().getName().equals(room.getName()) &&
                    ((startDate.before(m.getEndTime()) && endDate.after(m.getStartTime())))) {
                roomIsFree = false;
            }
        }
        if (!roomIsFree) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(this);
            myPopUp.setTitle("ATTENTION");
            myPopUp.setMessage("Cette salle n'est plus disponible.");
            myPopUp.setPositiveButton("OK", (dialogInterface, i) -> {
                // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
            });
            myPopUp.create().show();
            return;
        }


        // On transformer liste de String en liste de participants
        ArrayList<Participant> participantList = new ArrayList<>();
        for (Participant p : mApiService.getParticipants()) {
            if (participantNameList.contains(p.getName())) {
                participantList.add(p);
            }

        }

        Meeting newMeeting = new Meeting(System.currentTimeMillis(), meetingSubject, startDate, endDate, room, participantList);
        mApiService.addMeeting(newMeeting);
        finish();

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    // ========== POPUP ROOM =============

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void roomSelector(View view) {
        // Create list of rooms

        List<String> rooms = new ArrayList<>();

        for (Room room : mApiService.getRooms()) {
            rooms.add(room.getName());
        }
        CharSequence[] cs = rooms.toArray(new CharSequence[0]);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Sélectionner une salle"); // Set title of AlertDialog
        builder.setIcon(R.drawable.icon);
        builder.setSingleChoiceItems( cs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String roomSelected = rooms.get(i);
                buttonRoom.setText(roomSelected);
                room = mApiService.getRooms().get(i);
            }
        });

        // Set neutral cancel button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }
    // ======== End Toast Spinner Room end ===========


}


