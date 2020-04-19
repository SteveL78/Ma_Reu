package lamzone.com.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import lamzone.com.di.DI;
import lamzone.com.events.DeleteMeetingEvent;
import lamzone.com.model.Meeting;
import lamzone.com.service.MeetingApiService;

/**
 * Created by Steve LEROY on 07/04/2020.
 */
public class MeetingListFragment extends Fragment{


}