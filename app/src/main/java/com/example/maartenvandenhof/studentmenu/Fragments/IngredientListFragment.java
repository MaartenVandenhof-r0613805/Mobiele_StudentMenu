package com.example.maartenvandenhof.studentmenu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.Adapter.IngredientAdapter;
import com.example.maartenvandenhof.studentmenu.Adapter.MenuAdapter;
import com.example.maartenvandenhof.studentmenu.R;

public class IngredientListFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ingredient_list, container, false);
        RecyclerView lv = (RecyclerView)view.findViewById(R.id.ingredientList);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Ingredient List");
        IngredientAdapter adapter = new IngredientAdapter(getContext(), ((MainActivity)getActivity()).ingredientList);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
