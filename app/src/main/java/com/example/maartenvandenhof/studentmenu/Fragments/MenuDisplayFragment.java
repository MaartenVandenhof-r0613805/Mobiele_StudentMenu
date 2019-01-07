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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.Adapter.MenuAdapter;
import com.example.maartenvandenhof.studentmenu.Adapter.MenuIngredientListAdaptor;
import com.example.maartenvandenhof.studentmenu.R;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MenuDisplayFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu_display, container, false);
        TextView title = view.findViewById(R.id.menuDisplayTitle);
        TextView price = view.findViewById(R.id.menuDisplayPrice);
        TextView recipe = view.findViewById(R.id.recipeText);
        //ImageView image = view.findViewById(R.id.backgroundPicture);
        RecyclerView ingredientList = view.findViewById(R.id.menuIngredientRecycler);
        ((MainActivity)getActivity()).getSupportActionBar().setTitle("Have fun cooking!");
        String menuTitle = (String)getArguments().get("MenuTitle");
        String menuPrice = (String)getArguments().get("MenuPrice");
        String menuRecipe = (String)getArguments().get("MenuRecipe");

        //image
        price.setText(menuPrice);
        title.setText(menuTitle);
        recipe.setText(menuRecipe);
        MenuIngredientListAdaptor ingredientListAdaptor = new MenuIngredientListAdaptor(getContext(), (ArrayList<String>) getArguments().get("IngredientList"));
        ingredientList.setAdapter(ingredientListAdaptor);
        ingredientList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        return view;

    }
}
