package com.example.maartenvandenhof.studentmenu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.R;

public class GoToAddIngredientAllergiesFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_ingredient_allergies, container, false);
        TextView title = v.findViewById(R.id.ingredientTitle);
        title.setText(getArguments().getString("ingredientTitle"));
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Add ingredient");
        return v;
    }
}
