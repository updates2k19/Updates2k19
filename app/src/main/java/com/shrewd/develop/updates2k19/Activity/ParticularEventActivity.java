package com.shrewd.develop.updates2k19.Activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import com.shrewd.develop.updates2k19.R;

public class ParticularEventActivity extends AppCompatActivity {

    String eventId,eventName,eventDate,paid;
    TextView txt_pe_eventId,txt_pe_eventName,txt_pe_eventDate,txt_pe_paid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_particular_event);

        eventId=getIntent().getStringExtra("eventId");
        eventName=getIntent().getStringExtra("eventName");
        eventDate=getIntent().getStringExtra("eventDate");
        paid=getIntent().getStringExtra("paid");

        Log.e("ID ::: ",eventId);

        txt_pe_eventId=(TextView)findViewById(R.id.txt_pe_eventId);
        txt_pe_eventName=(TextView)findViewById(R.id.txt_pe_eventName);
        txt_pe_eventDate=(TextView)findViewById(R.id.txt_pe_eventDate);
        txt_pe_paid=(TextView)findViewById(R.id.txt_pe_paid);

        txt_pe_eventId.setText(eventId);
        txt_pe_eventName.setText(eventName);
        txt_pe_eventDate.setText(eventDate);
        txt_pe_paid.setText(paid);

    }
}
