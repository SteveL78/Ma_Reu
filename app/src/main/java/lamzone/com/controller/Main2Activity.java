package lamzone.com.controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

import lamzone.com.R;
import lamzone.com.di.DI;
import lamzone.com.service.MeetingApiService;

public class Main2Activity extends AppCompatActivity {


    private MeetingApiService mApiService = DI.getMeetingApiService();

    private TextView mWelcomeMsg;

    private EditText mMeetingSubject;

    private TextView mStartTxt;
    private Button mStartDateBtn;
    private Button mStartTimeBtn;

    private TextView mEndTxt;
    private Button mEndDateBtn;
    private Button mEndTimeBtn;


    private TextView mRoomTxt;
    private Spinner mSpinner;

    private TextView mParticipantsTxt;

    private Button mSaveBtn;



    /* Spinner to choose meeting room : */
/*    final ArrayList<String> meetingRooms = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.meeting_rooms_arrays)));
        meetingRooms.add(0, "Sélectionner une salle");
    ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, R.layout.spinner_item, meetingRooms) {*/


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        mWelcomeMsg = (TextView) findViewById(R.id.activity_main2_welcome_msg);
        mMeetingSubject = (EditText) findViewById(R.id.meeting_subject_editText);
        mStartTxt = (TextView) findViewById(R.id.start_textView);
        mStartDateBtn = (Button) findViewById(R.id.start_date_btn);
        mStartTimeBtn = (Button) findViewById(R.id.start_time_textView);
        mEndTxt = (TextView) findViewById(R.id.end_textView);
        mEndDateBtn = (Button) findViewById(R.id.end_date_btn);
        mEndTimeBtn = (Button) findViewById(R.id.end_time_btn);
        mRoomTxt = (TextView) findViewById(R.id.room_textView);

        mParticipantsTxt = (TextView) findViewById(R.id.participants_textView);
        mSaveBtn = (Button) findViewById(R.id.save_btn);

        // On désactive le bouton enregistrer tant que le
        mSaveBtn.setEnabled(false);
        mMeetingSubject.addTextChangedListener(new TextWatcher() {
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
        });


        mSaveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

/*
    mSpinner = findViewById(R.id.spinner1);
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.rooms, android.R.Layout.simple_spinner_item);

*/


        mApiService = DI.getMeetingApiService();


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}

