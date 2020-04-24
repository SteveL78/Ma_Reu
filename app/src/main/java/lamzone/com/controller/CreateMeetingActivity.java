package lamzone.com.controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.chrono.MinguoDate;
import java.util.Calendar;

import lamzone.com.R;
import lamzone.com.di.DI;
import lamzone.com.service.MeetingApiService;

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

    private TextView mParticipantsTv;
    private Spinner mSpinnerParticipant;

    private Button mSaveBtn;

    private DatePickerDialog mDatePickerDialog;
    private Calendar mCalendar;


    /* Spinner to choose meeting room : */
/*    final ArrayList<String> meetingRooms = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meeting_rooms_arrays)));
        meetingRooms.add(0, "Sélectionner une salle");
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meetingRooms) {


            // SPINNER ROOM
        ArrayAdapter<CharSequence> adapterRoom = ArrayAdapter.createFromResource(this, R.array.meeting_rooms_arrays, android.R.layout.simple_spinner_item);
        adapterRoom.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinnerRoom.setAdapter(adapterRoom);
        mSpinnerRoom.setOnItemSelectedListener(this);

        // SPINNER PARTICIPANT
        mSpinnerParticipant = (Spinner) findViewById(R.id.spinner_participant);
        ArrayAdapter<CharSequence> adapterParticipant = ArrayAdapter.createFromResource(this, R.array.meeting_participants_arrays, android.R.layout.simple_spinner_item);
        adapterParticipant.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        mSpinnerParticipant.setAdapter(adapterParticipant);
        mSpinnerParticipant.setOnItemSelectedListener(this);

    */






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meeting);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        mWelcomeMsg = (TextView) findViewById(R.id.activity_main2_welcome_msg);
        mMeetingSubjectTv = (EditText) findViewById(R.id.meeting_subject_editText);
        mStartTv = (TextView) findViewById(R.id.start_textView);
        mStartDateBtn = (Button) findViewById(R.id.select_date_btn);
        mEndTv = (TextView) findViewById(R.id.end_textView);
        mEndDateBtn = (Button) findViewById(R.id.select_end_btn);
        mRoomTv = (TextView) findViewById(R.id.select_room_textView);
        mSpinnerRoom = (Spinner) findViewById(R.id.spinner_room);
        mParticipantsTv = (TextView) findViewById(R.id.select_participant_textView);
        mSpinnerParticipant = (Spinner) findViewById(R.id.spinner_participant);
        mSaveBtn = (Button) findViewById(R.id.save_btn);


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        mApiService = DI.getMeetingApiService();

        mStartDateBtn.setOnClickListener(new View.OnClickListener() {           // DATETIMEPICKER (DEBUT DE LA REUNION)
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mStartDateBtn);
            }
        });




        mEndDateBtn.setOnClickListener(new View.OnClickListener() {           // DATETIMEPICKER (FIN DE LA REUNION)
            @Override
            public void onClick(View view) {
                showDateTimeDialog(mEndDateBtn);
            }
        });


        // SPINNER ROOM

        // Style and populate the Spinner

/*        ArrayAdapter<String> adapterRoom;
        adapterRoom = new ArrayAdapter<>(this, R.array.meeting_rooms_arrays, android.R.layout.simple_spinner_item);

        // Dropdown layout Style
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_item);

        // Attaching data adapter to spinner
        mSpinnerRoom.setAdapter(adapterRoom);

        mSpinnerRoom.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                if (adapterView.getItemAtPosition(position).equals("Sélectionnez une salle")) {
                    // do nothing
                } else {
                    // on selecting a spinner item
                    String item = adapterView.getItemAtPosition(position).toString();

                    // show selected spinner item
                    Toast.makeText(adapterView.getContext(), item + " sélectionnée", Toast.LENGTH_SHORT).show();

                    // anything else you want to do on item selection do here
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // TODO Auto-generated method stub
            }
        });*/













    }

    private void showDateTimeDialog(final Button mStartDateBtn) {          // SUITE DATETIMEPICKER (DEBUT DE LA REUNION)
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
                        mStartDateBtn.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(CreateMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }        // FIN DATETIMEPICKER (DEBUT DE LA REUNION)



    private void showDateTimeDialog2(final Button mEndDateBtn) {          // SUITE DATETIMEPICKER (FIN DE LA REUNION)
        final Calendar calendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yy HH:mm");
                        mEndDateBtn.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };

                new TimePickerDialog(CreateMeetingActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        };

        new DatePickerDialog(CreateMeetingActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
    }        // FIN DATETIMEPICKER (DEBUT DE LA REUNION)











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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }


    // Spinner Room

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
        Toast.makeText(adapterView.getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}


