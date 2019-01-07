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
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.maartenvandenhof.studentmenu.Activities.MainActivity;
import com.example.maartenvandenhof.studentmenu.R;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GoToAddMenuIngredientFragment extends Fragment {
    private LinearLayout mLayout;
    private EditText name;
    private EditText price;
    private Button mButton;
    LinearLayout ingredientC;
    LinearLayout priceC;
    LinearLayout invisibleAllergies;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_add_ingredient_menu, container, false);
        TextView title = v.findViewById(R.id.menuTitle);

        ingredientC = (LinearLayout) v.findViewById(R.id.addMenuIngredientColunm);
        priceC = (LinearLayout) v.findViewById(R.id.addMenuPriceColunm);
        invisibleAllergies = v.findViewById(R.id.invisibleAllergies);
        name = (EditText) v.findViewById(R.id.menuAddIngredientName);
        price = (EditText) v.findViewById(R.id.menuAddIngredientPrice);
        mButton = (Button) v.findViewById(R.id.addIngredientButton);
        mButton.setOnClickListener(onClick());
        TextView textView = new TextView((MainActivity)getActivity());
        textView.setText("New text");
        title.setText(getArguments().get("menuTitle").toString());
        return v;
    }

    private View.OnClickListener onClick() {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean ingredientExists = false;
                for( int i = 0; i < ingredientC.getChildCount(); i++) {
                    if (ingredientC.getChildAt(i) instanceof TextView) {
                        if (((TextView) ingredientC.getChildAt(i)).getText().equals(name.getText().toString().trim())) {
                            ingredientExists = true;
                        }
                    }
                }

                if (!ingredientExists && !name.getText().toString().isEmpty() && !price.getText().toString().isEmpty()){
                    ingredientC.addView(createNewTextView(name.getText().toString()));
                    priceC.addView(createNewTextView(price.getText().toString()));
                    invisibleAllergies.addView(createLinearLayoutWithList(((MainActivity) getActivity()).allergiesList));
                    ((MainActivity) getActivity()).allergiesList = new ArrayList<>();
                }
                if (name.getText().toString().isEmpty() || price.getText().toString().isEmpty()){
                    Toast.makeText((MainActivity)getActivity(), "Fill in a name and price", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private TextView createNewTextView(String text) {
        final TextView textView = new TextView((MainActivity)getActivity());
        textView.setText(text);
        textView.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        textView.setPadding(0,0,5,0);
        textView.setWidth(100);
        textView.setTextSize(12);
        return textView;
    }

    private LinearLayout createLinearLayoutWithList(ArrayList<String> list){
        final LinearLayout layout = new LinearLayout((MainActivity)getActivity());
        layout.setOrientation(LinearLayout.HORIZONTAL);
        layout.setMinimumWidth(20);
        for (String s:list){
            layout.addView(createNewTextView(s));
        }
        return layout;
    }
}
