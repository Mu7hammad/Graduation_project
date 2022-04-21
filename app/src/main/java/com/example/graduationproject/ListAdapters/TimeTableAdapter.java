package com.example.graduationproject.ListAdapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationproject.MainActivities.MainActivity;
import com.example.graduationproject.Models.TimeTableItem;
import com.example.graduationproject.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class TimeTableAdapter extends ArrayAdapter<TimeTableItem> {

    private Context mContext;
    private int resourseLayout;
    List<TimeTableItem> items;
    int[] colors = {R.color.dark_green, R.color.red, R.color.purple_500, R.color.teal_700, R.color.blue};
    int color_position = 0;

    public TimeTableAdapter(Context mContext, int resourseLayout, List<TimeTableItem> items) {
        super(mContext, resourseLayout, items);
        this.resourseLayout = resourseLayout;
        this.mContext = mContext;
        this.items = items;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if (v == null) {
            v = LayoutInflater.from(mContext).inflate(resourseLayout, null);
        }

        final TimeTableItem p = items.get(position);
        TextView date_tv = v.findViewById(R.id.date_tv);
        TextView time_tv = v.findViewById(R.id.time_tv);
        TextView first_letter_in_title_tv = v.findViewById(R.id.first_letter_in_title_tv);
        ProgressBar progress_bar = v.findViewById(R.id.progress_bar);
        TextView progress_tv = v.findViewById(R.id.progress_tv);
        TextView title_tv = v.findViewById(R.id.title_tv);
        TextView room_tv = v.findViewById(R.id.room_tv);
        final RelativeLayout back_rel = v.findViewById(R.id.back_rel);
        load_color(back_rel, position);
        date_tv.setText(p.getDate());
        time_tv.setText(p.getTime());
        first_letter_in_title_tv.setText(p.getTitle());
        progress_bar.setProgress(50);
        progress_tv.setText(50 + "%");
        title_tv.setText(p.getTitle());
        room_tv.setText("Room: " + p.getRoom());
        v.setOnClickListener(view -> {
//                mContext.startActivity(new Intent(mContext, CoursePreview.class).putExtra("course_name", p.getTitle()).putExtra("color", ((ColorDrawable) back_rel.getBackground()).getColor()));
            Toast.makeText(mContext, "open material page", Toast.LENGTH_SHORT).show();
        });

        return v;
    }

    private void load_color(RelativeLayout back_rel, int position) {
        if (position != 0 && !items.get(position).getTitle().equals(items.get(position - 1).getTitle())) {
            /* the previous position has different name, lets change color position */
            color_position++;
            if (color_position > colors.length - 1) {
                /* color position is out of array size, reset position */
                color_position = 0;
            }
        }
        back_rel.setBackground(mContext.getResources().getDrawable(colors[color_position]));
    }
}