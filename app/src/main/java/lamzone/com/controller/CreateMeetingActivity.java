package lamzone.com.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.DatabaseErrorHandler;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.w3c.dom.DOMStringList;

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
import lamzone.com.service.DummyMeetingGenerator;
import lamzone.com.service.MeetingApiService;
import lamzone.com.service.ParticipantGenerator;
import lamzone.com.ui.MyMeetingRecyclerViewAdapter;

public class CreateMeetingActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private CreateMeetingActivity activity;
    private MeetingApiService mApiService = DI.getMeetingApiService();

    private TextView mWelcomeMsg;

    private EditText mMeetingSubjectTv;

    private TextView mStartTv;
    private Button mStartDateBtn;

    private TextView mEndTv;
    private Button mEndDateBtn;

    private TextView mRoomTv;
    private Spinner mSpinnerRoom;

    private MultiAutoCompleteTextView multiAutoCompleteTextView;
    private List<ParticipantGenerator> participantGenerators;

    private Button mSaveBtn;

    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;

    private Calendar calendarStart = null;

    private Calendar calendarEnd = null;

    private Room room = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

/*        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);*/

        mMeetingSubjectTv = findViewById(R.id.meeting_subject_editText);
        mStartTv = findViewById(R.id.start_textView);
        mStartDateBtn = findViewById(R.id.select_date_btn);
        mEndTv = findViewById(R.id.end_textView);
        mEndDateBtn = findViewById(R.id.select_end_btn);
        mRoomTv = findViewById(R.id.select_room_textView);
        mSpinnerRoom = findViewById(R.id.spinner_room);
        mSaveBtn = findViewById(R.id.save_btn);

        multiAutoCompleteTextView = findViewById(R.id.multiautocompletetextview);

        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onCreateMeetingClicked();
            }
        });

        this.activity = this;
        mApiService = DI.getMeetingApiService();
        mApiService.getRooms();
        mApiService.getParticipants();


        // ============== DATETIME PICKER =================

        mStartDateBtn.setOnClickListener(new View.OnClickListener() {           // DATETIMEPICKER (DEBUT DE LA REUNION)
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mStartDateBtn);
            }
        });


        // ============== TIME PICKER =================

        mEndDateBtn.setOnClickListener(new View.OnClickListener() {     // DEBUT DU TIMEPICKER
            @Override
            public void onClick(View view) {
                showTimeDialogEnd(mEndDateBtn);
            }
        });


        // ============ SPINNER ROOM ==============

        List<String> rooms = new ArrayList<>();
        rooms.add(0, "Cliquer ici");
        for (Room room : mApiService.getRooms()) {
            rooms.add(room.getName());
        }

        // Style and populate the Spinner
        ArrayAdapter<String> dataAdapterRoom;
        dataAdapterRoom = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, rooms);

        // Dropdown layout Style
        dataAdapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // Attaching data adapter to spinner
        mSpinnerRoom.setAdapter(dataAdapterRoom);

        mSpinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (adapterView.getItemAtPosition(position).equals("Cliquer ici")) {
                    // do nothing

                } else {
                    room = mApiService.getRooms().get(position);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });


        // =========== MULTIAUTOCOMPLETETEXTVIEW PARTICIPANT ================

        List<String> participants = new ArrayList<>();
        for (Participant participant : mApiService.getParticipants()) {
            participants.add(participant.getParticipantName());
        }
        ArrayAdapter<String> arrayAdapterParticipant;
        arrayAdapterParticipant = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, participants);
        multiAutoCompleteTextView.setAdapter(arrayAdapterParticipant);
        multiAutoCompleteTextView.setThreshold(1);
        multiAutoCompleteTextView.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());


        // ============== DATETIME PICKER =================

        mStartDateBtn.setOnClickListener(new View.OnClickListener() {           // DATETIMEPICKER (DEBUT DE LA REUNION)
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mStartDateBtn);
            }
        });


        // ============== TIME PICKER =================

        mEndDateBtn.setOnClickListener(new View.OnClickListener() {     // DEBUT DU TIMEPICKER
            @Override
            public void onClick(View view) {
                showTimeDialogEnd(mEndDateBtn);
            }
        });
    }


    // =========== SUITE DATETIMEPICKER ================

    private void showDateTimeDialog(final Button mStartDateBtn) {          // SUITE DATETIMEPICKER (DEBUT DE LA REUNION)
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendarStart = Calendar.getInstance();
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendarStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendarStart.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
                        mStartDateBtn.setText(simpleDateFormat.format(calendarStart.getTimeInMillis()));
                    }
                };

                new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendarStart.get(Calendar.HOUR_OF_DAY), calendarStart.get(Calendar.MINUTE), true).show();
            }
        };

        final  Calendar calendar = Calendar.getInstance();
        DatePickerDialog dp = new DatePickerDialog(CreateMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        Date date = new Date();
        dp.getDatePicker().setMinDate(date.getTime());
        dp.show();
    }        // FIN DATETIMEPICKER (DEBUT DE LA REUNION)


    // =========== SUITE TIMEPICKER ================

    private void showTimeDialogEnd(final Button mEndDateBtn) {             // SUITE TIMEPICKER

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendarEnd = Calendar.getInstance();
                calendarEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarEnd.set(Calendar.MINUTE, minute);
                calendarEnd.set(Calendar.DAY_OF_MONTH, calendarStart.get(Calendar.DAY_OF_MONTH));
                calendarEnd.set(Calendar.MONTH, calendarStart.get(Calendar.MONTH));
                calendarEnd.set(Calendar.YEAR, calendarStart.get(Calendar.YEAR));

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mEndDateBtn.setText(simpleDateFormat.format(calendarEnd.getTimeInMillis()));
            }
        };

        final  Calendar calendar = Calendar.getInstance();
        new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
    }       // FIN TIMEPICKER


    // On désactive le bouton enregistrer tant que le
/*        mSaveBtn.setEnabled(false);
        mMeetingSubjectTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                mSaveBtn.setEnabled(charSequence.toString().length() != 0);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });*/



    // ============= VERIFICATION DES CHAMPS LORS DE L'ENREGISTREMENT  =============


    private void onCreateMeetingClicked() {

        // TODO vérifier la validité des champs + verifier que le topic n'est pas vide
        String meetingSubject = mMeetingSubjectTv.getText().toString();
        String multiParticipants = multiAutoCompleteTextView.getText().toString();

        if (meetingSubject.isEmpty()){
            // TODO : mettre une popup
            return;
        }



        if (calendarStart == null) {
            AlertDialog.Builder myPopUp = new AlertDialog.Builder(activity);
            myPopUp.setTitle("Attention");
            myPopUp.setMessage("Veillez à bien mettre une date et une heure de début");
            myPopUp.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // Toast.makeText(getApplicationContext(), "Cliquer sur OK pour continuer", Toast.LENGTH_LONG).show();
                }
            });
            myPopUp.create().show();

            return;

        }

        if (calendarEnd == null) {
            // TODO mettre popup date end pas initialisé
            return;

        }

        Date startDate = calendarStart.getTime();
        Date endDate = calendarEnd.getTime();


        // On vérifie que la date de fin n'est pas antérieure à la date de début
        if (startDate.after(endDate)) {
            // TODO mettre popup
            return;
        }

        if (room == null){
            // TODO : mettre popup
            return;
        }

        String [] participants = multiAutoCompleteTextView.getText().toString().split(",");
        if (participants.length == 0){
            // TODO : mettre popup
            return;

        }


        // TODO vérifier la disponibilité de la salle




    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    // ========== Toast Spinner Room =============

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        String text = adapterView.getItemAtPosition(position).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
    // ======== End Toast Spinner Room end ===========


}


