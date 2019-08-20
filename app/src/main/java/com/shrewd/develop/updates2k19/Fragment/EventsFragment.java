package com.shrewd.develop.updates2k19.Fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.shrewd.develop.updates2k19.Adapter.EventAdapter;
import com.shrewd.develop.updates2k19.Model.EventDetail;
import com.shrewd.develop.updates2k19.R;
import com.shrewd.develop.updates2k19.Utilities.CS;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class EventsFragment extends Fragment {


    private Context mContext;
    private RecyclerView rvEvents;
    private ArrayList<EventDetail> alEventDetail = new ArrayList<>();
    private final String TAG = "EventsFragment";

    public EventsFragment() {
        // Required empty public constructor
    }

    public EventsFragment(Context mContext) {
        this.mContext = mContext;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_events, container, false);
        rvEvents = (RecyclerView)view.findViewById(R.id.rvEvents);
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("event")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            if (task.getResult() == null) {
                                return;
                            }
                            alEventDetail.clear();
                            for (QueryDocumentSnapshot documentSnapshot : task.getResult()) {
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.description));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.event_type));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.faculty_coordinator));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.flyer));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.icon_url));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.ln_hindi));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.name));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.poster));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.qualified_participants));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.schedule));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.student_coordinator));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.student_volunteer));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.tagline));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.end_time));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.location_desc));
                                Log.e(TAG, "onComplete: "+ documentSnapshot.get(CS.start_time));

                                EventDetail event = new EventDetail(String.valueOf(documentSnapshot.get(CS.description)),
                                        String.valueOf(documentSnapshot.get(CS.event_type)),String.valueOf(documentSnapshot.get(CS.faculty_coordinator)),
                                        String.valueOf(documentSnapshot.get(CS.flyer)),String.valueOf(documentSnapshot.get(CS.icon_url)),
                                        String.valueOf(documentSnapshot.get(CS.ln_hindi)),String.valueOf(documentSnapshot.get(CS.name)),
                                        String.valueOf(documentSnapshot.get(CS.poster)),String.valueOf(documentSnapshot.get(CS.qualified_participants)),
                                        String.valueOf(documentSnapshot.get(CS.schedule)),String.valueOf(documentSnapshot.get(CS.student_coordinator)),
                                        String.valueOf(documentSnapshot.get(CS.student_volunteer)),String.valueOf(documentSnapshot.get(CS.tagline)),
                                        String.valueOf(documentSnapshot.get(CS.end_time)),String.valueOf(documentSnapshot.get(CS.location_desc)),
                                        String.valueOf(documentSnapshot.get(CS.start_time)));
                                alEventDetail.add(event);
                            }
                            EventAdapter adapter = new EventAdapter(mContext,alEventDetail);
                            rvEvents.setLayoutManager(new LinearLayoutManager(mContext,RecyclerView.VERTICAL,false));
                            rvEvents.setAdapter(adapter);
                        }
                    }
                });
        return view;
    }

}
