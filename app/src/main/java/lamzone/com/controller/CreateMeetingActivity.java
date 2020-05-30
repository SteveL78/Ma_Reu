package lamzone.com.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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
                if (adapterView.getItemAtPosition(position).equals("Sélectionnez une salle")) {
                    // do nothing
                } else {
                    // on selecting a spinner item
                    String item = adapterView.getItemAtPosition(position).toString();

                    // show selected spinner item
                    Toast.makeText(adapterView.getContext(), "Salle " + item + " sélectionnée", Toast.LENGTH_SHORT).show();

                    // anything else you want to do on item selection do here
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
        final Calendar calendarStart = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendarStart.set(Calendar.YEAR, year);
                calendarStart.set(Calendar.MONTH, month);
                calendarStart.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendarStart.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendarStart.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm", Locale.getDefault());
                        mStartDateBtn.setText(simpleDateFormat.format(calendarStart.getTime()));
                    }
                };

                new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendarStart.get(Calendar.HOUR_OF_DAY), calendarStart.get(Calendar.MINUTE), true).show();
            }
        };

        DatePickerDialog dp = new DatePickerDialog(CreateMeetingActivity.this, dateSetListener, calendarStart.get(Calendar.YEAR), calendarStart.get(Calendar.MONTH), calendarStart.get(Calendar.DAY_OF_MONTH));
        Date date = new Date();
        dp.getDatePicker().setMinDate(date.getTime());
        dp.show();
    }        // FIN DATETIMEPICKER (DEBUT DE LA REUNION)


    // =========== SUITE TIMEPICKER ================

    private void showTimeDialogEnd(final Button mEndDateBtn) {             // SUITE TIMEPICKER
        final Calendar calendarEnd = Calendar.getInstance();

        TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                calendarEnd.set(Calendar.HOUR_OF_DAY, hourOfDay);
                calendarEnd.set(Calendar.MINUTE, minute);
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                mEndDateBtn.setText(simpleDateFormat.format(calendarEnd.getTime()));
            }
        };

        new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendarEnd.get(Calendar.HOUR_OF_DAY), calendarEnd.get(Calendar.MINUTE), true).show();
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

        // TODO vérifier la validité des champs + verifier que le topic n'est pas vide +
        // TODO que les dates sont bien sélectionnées, que la salle est sélectionnée et 1 utilisateur

        // On vérifie que le Topic n'est pas vide
        String meetingSubject = mMeetingSubjectTv.getText().toString();
        if (TextUtils.isEmpty(meetingSubject)) {
            mMeetingSubjectTv.setError("Veuillez SVP préciser l'objet de la réunion");
            return;
        }


        // On vérifie que la date de début n'est pas vide
        String startDate = mStartDateBtn.getText().toString();
        if (TextUtils.isEmpty(startDate)) {
            mStartDateBtn.setError("Veuillez SVP préciser une date de début");
            return;
        }

        // On vérifie que la date de fin n'est pas vide
        String endDate = mEndDateBtn.getText().toString();
        if (TextUtils.isEmpty(endDate)) {
            mEndDateBtn.setError("Veuillez SVP préciser une date de fin");
            return;
        }

        // On vérifie que la date de fin n'est pas vide
        String multiParticipants = multiAutoCompleteTextView.getText().toString();
        if (TextUtils.isEmpty(multiParticipants)) {
            multiAutoCompleteTextView.setError("Veuillez SVP renseigner au moins un participant");
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


