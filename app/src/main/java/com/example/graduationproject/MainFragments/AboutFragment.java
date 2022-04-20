package com.example.graduationproject.MainFragments;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.graduationproject.R;

public class AboutFragment extends Fragment {


    ImageView facebook_btn, whatsapp_btn, youtube_btn;
    ImageButton call_btn;
    boolean is_expand_1 = false,is_expand_2 = false,is_expand_3 = false,is_expand_4 = false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        declaration(view);
        onClick(view);

        return view;
    }

    private void onClick(View v) {
        facebook_btn.setOnClickListener(view -> {
            try {
                getActivity().getPackageManager().getPackageInfo("com.facebook.katana", PackageManager.GET_ACTIVITIES);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("fb://page/176552522737276")));
            } catch (Exception e) {
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://www.facebook.com/CIS.EDU1")));
            }
        });

        whatsapp_btn.setOnClickListener(view -> {
            try {
                getActivity().getPackageManager().getPackageInfo("com.whatsapp", PackageManager.GET_ACTIVITIES);
                startActivity(new Intent(Intent.ACTION_VIEW).setData(Uri.parse("https://api.whatsapp.com/send?phone=+201207650873")));
            } catch (Exception e) {
            }
        });

        youtube_btn.setOnClickListener(view -> startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UCoRUJ2St0Bx7WjKdbTqOvkQ"))));

        call_btn.setOnClickListener(view -> {
            if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.CALL_PHONE}, 0);
            } else {
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:19622")));
            }
        });

        v.findViewById(R.id.row1).setOnClickListener(view -> {
            is_expand_1 = !is_expand_1;
            v.findViewById(R.id.description_1).setVisibility(is_expand_1? View.VISIBLE:View.GONE);
            ImageView i = v.findViewById(R.id.expand_btn_1);
            i.setImageResource(is_expand_1?R.drawable.ic_arrow_up :R.drawable.ic_arrow_down);
            TextView t = v.findViewById(R.id.title_1);
            t.setTextColor(getResources().getColor(is_expand_1?R.color.purple_500:R.color.black));
        });

        v.findViewById(R.id.row2).setOnClickListener(view -> {
            is_expand_2 = !is_expand_2;
            v.findViewById(R.id.description_2).setVisibility(is_expand_2? View.VISIBLE:View.GONE);
            ImageView i = v.findViewById(R.id.expand_btn_2);
            i.setImageResource(is_expand_2?R.drawable.ic_arrow_up :R.drawable.ic_arrow_down);
            TextView t = v.findViewById(R.id.title_2);
            t.setTextColor(getResources().getColor(is_expand_2?R.color.purple_500:R.color.black));
        });

        v.findViewById(R.id.row3).setOnClickListener(view -> {
            is_expand_3 = !is_expand_3;
            v.findViewById(R.id.description_3).setVisibility(is_expand_3? View.VISIBLE:View.GONE);
            ImageView i = v.findViewById(R.id.expand_btn_3);
            i.setImageResource(is_expand_3?R.drawable.ic_arrow_up :R.drawable.ic_arrow_down);
            TextView t = v.findViewById(R.id.title_3);
            t.setTextColor(getResources().getColor(is_expand_3?R.color.purple_500:R.color.black));
        });

        v.findViewById(R.id.row4).setOnClickListener(view -> {
            is_expand_4 = !is_expand_4;
            v.findViewById(R.id.description_4).setVisibility(is_expand_4? View.VISIBLE:View.GONE);
            ImageView i = v.findViewById(R.id.expand_btn_4);
            i.setImageResource(is_expand_4?R.drawable.ic_arrow_up :R.drawable.ic_arrow_down);
            TextView t = v.findViewById(R.id.title_4);
            t.setTextColor(getResources().getColor(is_expand_4?R.color.purple_500:R.color.black));
        });
    }

    private void declaration(View v) {
        facebook_btn = v.findViewById(R.id.facebook_btn);
        whatsapp_btn = v.findViewById(R.id.whatsapp_btn);
        youtube_btn = v.findViewById(R.id.youtube_btn);
        call_btn = v.findViewById(R.id.call_btn);
    }
}