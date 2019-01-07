package com.example.maartenvandenhof.studentmenu.Fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.Adapter.MenuAdapter;
import com.example.maartenvandenhof.studentmenu.Menu;
import com.example.maartenvandenhof.studentmenu.R;

import java.util.ArrayList;
import java.util.Collections;

public class MenuPriceSearchFragment extends Fragment {
    double price;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_list, container, false);
        RecyclerView lv = view.findViewById(R.id.menuList);

        ArrayList<Menu> menuList = ((MainActivity)getActivity()).menuList;
        ArrayList<String> allergies = ((MainActivity) getActivity()).allergiesList;
        ArrayList<Menu> adjustedList = new ArrayList<>();
        double price = getArguments().getDouble("price");
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("All Menus under " + price);
        for (Menu m : menuList){
            if(m.getPrice() <= price && Collections.disjoint(m.getAllergies(), allergies)){
                adjustedList.add(m);
            }
        }
        ((MainActivity) getActivity()).allergiesList = new ArrayList<String>();
        MenuAdapter adapter = new MenuAdapter(getContext(), adjustedList);
        lv.setAdapter(adapter);
        lv.setLayoutManager(new LinearLayoutManager(getContext()));
        return view;
    }
}
