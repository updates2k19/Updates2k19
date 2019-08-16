package com.shrewd.develop.updates2k19;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class EventAdapter_np extends RecyclerView.Adapter<EventAdapter_np.ViewHolder> {

    Context context;
    ArrayList<EventDetailClass_np> arrayList;
    LayoutInflater inflater;

    public EventAdapter_np(Context context, ArrayList<EventDetailClass_np> arrayList) {
        this.context=context;
        this.arrayList=arrayList;
        inflater=LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view=inflater.inflate(R.layout.cardviewmodel,viewGroup,false);

        ViewHolder holder=new ViewHolder(view);
        holder.txt_eId=(TextView)view.findViewById(R.id.txt_eId);
        holder.txt_eName=(TextView)view.findViewById(R.id.txt_eName);
        holder.txt_eDate=(TextView)view.findViewById(R.id.txt_eDate);
        holder.txt_ePaid=(TextView)view.findViewById(R.id.txt_ePaid);
        holder.cardView=(CardView)view.findViewById(R.id.cardView);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.txt_eId.setText(arrayList.get(i).getEventId());
        viewHolder.txt_eName.setText(arrayList.get(i).getEventName());
        viewHolder.txt_eDate.setText(arrayList.get(i).getEventDate());
        viewHolder.txt_ePaid.setText(arrayList.get(i).getPaid());

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,ParticularEvent_np.class);
                intent.putExtra("eventId",arrayList.get(i).eventId.toString());
                intent.putExtra("eventName",arrayList.get(i).getEventName().toString());
                intent.putExtra("eventDate",arrayList.get(i).getEventDate().toString());
                intent.putExtra("paid",arrayList.get(i).getPaid().toString());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txt_eName,txt_eDate,txt_eId,txt_ePaid;
        CardView cardView;
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
