package com.example.graduationproject.MainActivities;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.graduationproject.MainFragments.AboutFragment;
import com.example.graduationproject.MainFragments.HomeFragment;
import com.example.graduationproject.MainFragments.NewsFragment;
import com.example.graduationproject.MainFragments.SearchFragment;
import com.example.graduationproject.MainFragments.StudentProfileFragment;
import com.example.graduationproject.Models.Student;
import com.example.graduationproject.Others.StaticVar;
import com.example.graduationproject.R;
import com.example.graduationproject.SubFragments.ResultFragment;

import java.util.ArrayList;
import java.util.List;

import kotlin.Unit;
import kotlin.jvm.functions.Function1;

public class MainActivity extends AppCompatActivity {
    View decorView;
    HomeFragment homeFragment;
    StudentProfileFragment studentProfileFragment;
    AboutFragment aboutFragment;
    SearchFragment searchFragment;
    NewsFragment newsFragment;
    ResultFragment resultFragment;
    private MeowBottomNavigation bnv_main;
    List<Student> student_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        homeFragment = new HomeFragment();
        studentProfileFragment = new StudentProfileFragment();
        aboutFragment = new AboutFragment();
        searchFragment = new SearchFragment();
        newsFragment = new NewsFragment();
        resultFragment = new ResultFragment();


        decorView = getWindow().getDecorView();
        decorView.setOnSystemUiVisibilityChangeListener(new View.OnSystemUiVisibilityChangeListener() {
            @Override
            public void onSystemUiVisibilityChange(int visibility) {
                if (visibility == 0) {
                    decorView.setSystemUiVisibility(hideSystemBars());
                }
            }
        });


        // Custom Bottom Navigation
        bnv_main = findViewById(R.id.bnv_main);
        bnv_main.add(new MeowBottomNavigation.Model(1, R.drawable.ic_profile));
        bnv_main.add(new MeowBottomNavigation.Model(2, R.drawable.ic_news));
        bnv_main.add(new MeowBottomNavigation.Model(3, R.drawable.ic_home));
        bnv_main.add(new MeowBottomNavigation.Model(4, R.drawable.ic_about));
        bnv_main.add(new MeowBottomNavigation.Model(5, R.drawable.ic_search));
        bnv_main.show(3, true);
        replace(new HomeFragment());

        // When chose icon
        bnv_main.setOnClickMenuListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                switch (model.getId()) {
                    case 1:
                        replace(studentProfileFragment);
                        break;
                    case 2:
                        replace(newsFragment);
                        break;
                    case 3:
                        replace(homeFragment);
                        break;
                    case 4:
                        replace(aboutFragment);
                        break;
                    case 5:
                        replace(searchFragment);
                        break;
                }
                return null;
            }
        });
        // When tap icon gain or reselect the same icon
        bnv_main.setOnReselectListener(new Function1<MeowBottomNavigation.Model, Unit>() {
            @Override
            public Unit invoke(MeowBottomNavigation.Model model) {
                return null;
            }
        });


        get_students_data();
    }

    private void get_students_data() {
        /* TODO Get data from database */
//        new Handler().postDelayed(() -> {
            /* get all students in our database */
            student_list.clear();
            student_list.add(new Student("Mohammed Wael", "C1700832", 2.15f, "Computer science", "Fourth level", "English", 1500));
            student_list.add(new Student("Mohammed Ibrahim", "C1700831", 3.15f, "Computer science", "Fourth level", "English", 2500));
            /* detect current student by code */
            detect_current_student();
//        }, StaticVar.data_load_delay);
    }

    private void detect_current_student() {
        for (int i = 0; i < student_list.size(); i++) {
            if (student_list.get(i).getCode().toLowerCase().equals("C1700831".toLowerCase())) {
                /* detected !! */
                StaticVar.student = student_list.get(i);
                return;
            }
        }
    }

    // Hide status app
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            decorView.setSystemUiVisibility(hideSystemBars());
        }
    }

    private int hideSystemBars() {
        return View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION;
    }

    // Fragment Viewer
    private void replace(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }
}
















