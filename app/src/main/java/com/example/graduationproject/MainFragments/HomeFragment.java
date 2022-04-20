package com.example.graduationproject.MainFragments;

import android.os.Bundle;

import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Transformation;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationproject.ListAdapters.TimeTableAdapter;
import com.example.graduationproject.Models.TimeTableItem;
import com.example.graduationproject.Others.StaticVar;
import com.example.graduationproject.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class HomeFragment extends Fragment {

    Calendar calendar;
    String[] days_array = new String[]{"Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday"};
    String[] days_array_ar = new String[]{"السبت", "الأحد", "الإثنين", "الثلاثاء", "الأربعاء", "الخميس", "الجمعه"};
    TextView today_lectures_tv, nothing_lectures_tv, username_tv;
    List<TimeTableItem> lectures_list = new ArrayList<>();
    ListView list_view;
    TextView fees_value_tv, gpa_value_tv;
    LinearLayout fees_layout, gpa_layout;
    ProgressBar lectures_progress;
    NestedScrollView scroll_view;
    Animation show_animation;
    List<TimeTableItem> time_table_items = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        declaration(view);
        load_username();
        get_time_table_items();
        load_lectures();
        load_fees();
        load_gpa();
        onClick(view);

        return view;
    }

    private void load_gpa() {
        gpa_value_tv.setText(StaticVar.student.getGpa() + "");
    }

    private void load_fees() {
        fees_value_tv.setText(StaticVar.student.getFees() + " EGP");
    }


    private void onClick(View view) {
        view.findViewById(R.id.materials).setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "open enrollment page", Toast.LENGTH_SHORT).show();
        });

        view.findViewById(R.id.results).setOnClickListener(view1 -> {
            Toast.makeText(getActivity(), "open results page", Toast.LENGTH_SHORT).show();
        });
    }

    private void load_username() {
        username_tv.setText(StaticVar.student.getName());
    }

    private void load_lectures() {
        lectures_list.clear();
        /* check if we have classes today */
        int day_of_week = calendar.get(Calendar.DAY_OF_WEEK) + 1;
        if (day_of_week > 7) {
            day_of_week = 1;
        }
//        String today = days_array[day_of_week - 1];
        String today ="السبت";
        Calendar tomroow_cal = Calendar.getInstance();
        tomroow_cal.add(Calendar.DAY_OF_YEAR, 1);
        int day_of_week_t = tomroow_cal.get(Calendar.DAY_OF_WEEK) + 1;
        if (day_of_week_t > 7) {
            day_of_week_t = 1;
        }
//        String tomorrow = days_array[day_of_week_t - 1];
        String tomorrow = "الأحد";
        ///////////
        if (today_is_end()) {
            today_lectures_tv.setText("محاضرات غداً");
            /* load tomorrow lectures */
            for (int i = 0; i < time_table_items.size(); i++) {
                if (time_table_items.get(i).getDate().equals(tomorrow)) {
                    lectures_list.add(time_table_items.get(i));
                }
            }
        } else {
            /* load today lectures */
            for (int i = 0; i < time_table_items.size(); i++) {
                if (time_table_items.get(i).getDate().equals(today)) {
                    lectures_list.add(time_table_items.get(i));
                }
            }
        }
        load_adapter();
    }

    private void load_adapter() {
        if (lectures_list.size() > 0) {
            TimeTableAdapter itemsAdapter = new TimeTableAdapter(this.getContext(), R.layout.time_table_item, lectures_list);
            list_view.setAdapter(itemsAdapter);
            list_view.setFocusable(false);
            list_view.setVisibility(View.VISIBLE);
            lectures_progress.setVisibility(View.GONE);
        } else {
            lectures_progress.setVisibility(View.GONE);
            nothing_lectures_tv.setVisibility(View.VISIBLE);
        }
    }

    private void declaration(View v) {
        calendar = Calendar.getInstance();
        today_lectures_tv = v.findViewById(R.id.today_lectures_tv);
        nothing_lectures_tv = v.findViewById(R.id.nothing_lectures_tv);
        list_view = v.findViewById(R.id.list_view);
        username_tv = v.findViewById(R.id.username);
        fees_value_tv = v.findViewById(R.id.fees_value_tv);
        gpa_value_tv = v.findViewById(R.id.gpa_value_tv);
        fees_layout = v.findViewById(R.id.fees_layout);
        gpa_layout = v.findViewById(R.id.gpa_layout);
        lectures_progress = v.findViewById(R.id.lectures_progress);
        scroll_view = v.findViewById(R.id.scroll_view);
        show_animation = AnimationUtils.loadAnimation(getActivity(), R.anim.show_animation);
    }

    private boolean today_is_end() {
        return calendar.get(Calendar.HOUR_OF_DAY) >= 16;
    }

    private void expand(final View v) {
        int matchParentMeasureSpec = View.MeasureSpec.makeMeasureSpec(((View) v.getParent()).getWidth(), View.MeasureSpec.EXACTLY);
        int wrapContentMeasureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        v.measure(matchParentMeasureSpec, wrapContentMeasureSpec);
        final int targetHeight = (v.getMeasuredHeight());
        v.getLayoutParams().height = 1;
        v.setVisibility(View.VISIBLE);
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                v.getLayoutParams().height = interpolatedTime == 1
                        ? LinearLayout.LayoutParams.WRAP_CONTENT
                        : (int) (targetHeight * interpolatedTime);
                v.requestLayout();
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (targetHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
    }

    private void collapse(final View v) {
        final int initialHeight = v.getMeasuredHeight();
        Animation a = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                if (interpolatedTime == 1) {
                    v.setVisibility(View.GONE);
                } else {
                    v.getLayoutParams().height = initialHeight - (int) (initialHeight * interpolatedTime);
                    v.requestLayout();
                }
            }

            @Override
            public boolean willChangeBounds() {
                return true;
            }
        };
        a.setDuration((int) (initialHeight / v.getContext().getResources().getDisplayMetrics().density));
        v.startAnimation(a);
        a.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {

            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
    }

    private void get_time_table_items() {
        /* TODO Get data from database */
//        new Handler().postDelayed(() -> {
            time_table_items.clear();
            time_table_items.add(new TimeTableItem("Network programming", days_array_ar[0], "01:00 PM", "312", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Network programming", days_array_ar[0], "02:30 PM", "312", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Artificial intelligence", days_array_ar[1], "09:30 AM", "412", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Artificial intelligence", days_array_ar[1], "11:00 AM", "412", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Internet technology", days_array_ar[1], "02:30 PM", "412", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Internet technology", days_array_ar[3], "09:30 AM", "312", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Software engineering 2", days_array_ar[3], "11:30 AM", "412", "D. Ahmed bayomi"));
            time_table_items.add(new TimeTableItem("Software engineering 2", days_array_ar[3], "01:00 PM", "412", "D. Ahmed bayomi"));
//        }, StaticVar.data_load_delay);
    }
}