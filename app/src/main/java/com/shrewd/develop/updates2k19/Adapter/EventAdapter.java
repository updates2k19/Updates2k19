package com.shrewd.develop.updates2k19.Adapter;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shrewd.develop.updates2k19.Model.EventDetail;
import com.shrewd.develop.updates2k19.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {

    private ArrayList<EventDetail> alEventDetail;
    private Context mContext;

    public EventAdapter(Context mContext, ArrayList<EventDetail> alEventDetail) {
        this.mContext = mContext;
        this.alEventDetail = alEventDetail;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.one_event,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        EventDetail eventDetail = alEventDetail.get(position);
        Picasso.get().load(eventDetail.getIcon_url()).placeholder(R.mipmap.logo).into(holder.civIcon);
        holder.tvName.setText(eventDetail.getName());
        holder.tvDate.setText(eventDetail.getStart_time());
        holder.tvType.setText(eventDetail.getEvent_type());
        holder.tvTagLine.setText(eventDetail.getName());
    }

    @Override
    public int getItemCount() {
        return alEventDetail.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName,tvDate,tvType,tvTagLine;
        private CircleImageView civIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            civIcon = (CircleImageView)itemView.findViewById(R.id.civIcon);
            tvName = (TextView)itemView.findViewById(R.id.tvName);
            tvDate = (TextView)itemView.findViewById(R.id.tvDate);
            tvType = (TextView)itemView.findViewById(R.id.tvType);
            tvTagLine = (TextView)itemView.findViewById(R.id.tvTagLine);
        }
    }
}
