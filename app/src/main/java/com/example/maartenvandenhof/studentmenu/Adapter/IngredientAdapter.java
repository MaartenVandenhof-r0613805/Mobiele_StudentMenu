package com.example.maartenvandenhof.studentmenu.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.maartenvandenhof.studentmenu.Ingredient;
import com.example.maartenvandenhof.studentmenu.Menu;
import com.example.maartenvandenhof.studentmenu.R;

import java.util.List;

public class IngredientAdapter extends RecyclerView.Adapter {

    Context myContext;
    List<Ingredient> ingredientData;


    public IngredientAdapter(Context myContext, List<Ingredient> menuData){
        this.myContext = myContext;
        this.ingredientData = menuData;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {


        LayoutInflater inflater = LayoutInflater.from(myContext);
        View v = inflater.inflate(R.layout.ingredient_list_view, viewGroup, false);
        return new myViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        ((myViewHolder) viewHolder).ingredientName.setText(ingredientData.get(i).getName());
        ((myViewHolder) viewHolder).ingredientDescription.setText(ingredientData.get(i).getDescription());
        ((myViewHolder) viewHolder).ingredientPrice.setText("€" +  ingredientData.get(i).getPrice());

    }

    @Override
    public int getItemCount() {
        return ingredientData.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        TextView ingredientName, ingredientDescription, ingredientPrice;


        public myViewHolder(View itemView){
            super(itemView);
            ingredientName = itemView.findViewById(R.id.ingredientName);
            ingredientDescription = itemView.findViewById(R.id.ingredientDescription);
            ingredientPrice = itemView.findViewById(R.id.ingredientPrice);
        }
    }
}
