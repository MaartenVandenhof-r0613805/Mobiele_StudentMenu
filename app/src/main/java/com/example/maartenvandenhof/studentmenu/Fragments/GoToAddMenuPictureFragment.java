package com.example.maartenvandenhof.studentmenu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.R;

import java.io.File;

public class GoToAddMenuPictureFragment extends Fragment {

    public Button btnChoose, btnUpload;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_picture, container, false);
        TextView title = v.findViewById(R.id.addMenuTitle);
        title.setText(getArguments().get("menuTitle").toString());
        btnChoose = (Button) v.findViewById(R.id.bSelectloadImage);
        btnUpload = (Button) v.findViewById(R.id.bUploadImage2);
        ((MainActivity)getActivity()).imageView = (ImageView) v.findViewById(R.id.imageView);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).chooseImage();
            }
        });

        btnUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).uploadImage();
            }
        });
        return v;
    }
}