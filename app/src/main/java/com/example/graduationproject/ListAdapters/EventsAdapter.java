package com.example.graduationproject.ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.graduationproject.Models.EventItem;
import com.example.graduationproject.R;

import java.util.List;

import androidx.recyclerview.widget.RecyclerView;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.MyViewHolder> {

    private Context context;
    private List<EventItem> items;

    public class MyViewHolder extends RecyclerView.ViewHolder {

        LinearLayout main_lin;
        TextView title_tv;
        TextView date_tv;
        TextView description_tv;
        ImageView back_img;
        ImageView expand_img;

        public MyViewHolder(View view) {
            super(view);
            main_lin = view.findViewById(R.id.main_lin);
            title_tv = view.findViewById(R.id.title_tv);
            date_tv = view.findViewById(R.id.date_tv);
            back_img = view.findViewById(R.id.back_img);
            expand_img = view.findViewById(R.id.expand_img);
            description_tv = view.findViewById(R.id.description_tv);
        }
    }

    public EventsAdapter(Context context, List<EventItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        final EventItem p = items.get(position);
        holder.title_tv.setText(p.getTitle());
        holder.date_tv.setText(p.getDate());
        holder.description_tv.setText(p.getDescription());
        if (!p.getImage_url().equals("")) {
            Glide.with(context).load(p.getImage_url()).into(holder.back_img);
        } else {
            Glide.with(context).load(context.getResources().getDrawable(R.drawable.logo_white)).into(holder.back_img);
        }
        holder.main_lin.setOnClickListener(view -> {
//                context.startActivity(new Intent(context, Event.class).putExtra("title", p.getTitle()).putExtra("date", p.getDate()).putExtra("description", p.getDescription()).putExtra("image_url", p.getImage_url()));
            if(holder.description_tv.getVisibility() ==View.VISIBLE){
                holder.description_tv.setVisibility(View.GONE);
                holder.expand_img.setImageResource(R.drawable.ic_arrow_down);
            } else {
                holder.description_tv.setVisibility(View.VISIBLE);
                holder.expand_img.setImageResource(R.drawable.ic_arrow_up);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.event_item, parent, false);
        return new MyViewHolder(v);
    }
}
