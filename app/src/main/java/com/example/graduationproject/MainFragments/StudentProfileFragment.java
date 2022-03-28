package com.example.graduationproject.MainFragments;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.graduationproject.R;
import com.sdsmdg.tastytoast.TastyToast;

public class StudentProfileFragment extends Fragment {

    ImageView copy_code;
    TextView profile_code;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_student_profile, container, false);

        profile_code = view.findViewById(R.id.profile_code);
        // Copy Student Code
        copy_code = view.findViewById(R.id.copy_code);
        copy_code.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("TextView", profile_code.getText().toString());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getActivity(), "Copied", Toast.LENGTH_SHORT).show();
            }
        });


        return view;
    }
}