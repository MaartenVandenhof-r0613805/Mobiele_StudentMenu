package com.example.maartenvandenhof.studentmenu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.Menu;
import com.example.maartenvandenhof.studentmenu.R;

import java.util.ArrayList;
import java.util.List;

public class MenuIngredientListAdaptor extends RecyclerView.Adapter implements View.OnClickListener {

    Context myContext;
    ArrayList<String> ingredients;




    public MenuIngredientListAdaptor(Context myContext, ArrayList<String> ingredients){
        this.myContext = myContext;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater = LayoutInflater.from(myContext);
        View v = inflater.inflate(R.layout.menu_ingredient_list_view, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((myViewHolder) viewHolder).ingredient.setText(ingredients.get(i));

    }

    @Override
    public int getItemCount() {
        return ingredients.size();
    }

    @Override
    public void onClick(View view) {

    }


    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView ingredient;

        public myViewHolder(View itemView){
            super(itemView);
            ingredient = itemView.findViewById(R.id.ingredient);
        }
    }
}
