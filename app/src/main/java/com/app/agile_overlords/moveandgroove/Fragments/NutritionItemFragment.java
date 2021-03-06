package com.app.agile_overlords.moveandgroove.Fragments;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.agile_overlords.moveandgroove.CalorieConsumed;
import com.app.agile_overlords.moveandgroove.Models.NutritionItemModel;
import com.app.agile_overlords.moveandgroove.R;
import com.bumptech.glide.Glide;

/**
 * Created by brittneyryn on 4/9/16.
 */
public class NutritionItemFragment extends Fragment {
    private NutritionItemModel item;
    private static final String ITEM = "recipe";
    Context mContext;
    private TextView detailName;
    private TextView calories;
    private TextView calFat;
    private TextView sizeUnit;
    private EditText enterNumber;
    private Button addFood, clearFood;
    private float cals_consumed;
    private TextView carbs;
    private int edit;
    private TextView fiber;
    private TextView sugar;
    private TextView sodium;

    public NutritionItemFragment() {
        // Required empty public constructor
    }

    // A RecipeItemModel is passed in during instantiation for addition to the fragment's args
    public static NutritionItemFragment newInstance(NutritionItemModel item) {
        NutritionItemFragment fragment = new NutritionItemFragment();
        Bundle args = new Bundle();
        args.putSerializable(ITEM, item);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            item = (NutritionItemModel)getArguments().getSerializable(ITEM);
        }
    }

    // Once the fragment's view has been created, the RecipeItemModel data is assigned to the various
    // layout elements.
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_nutrition, container, false);
        //recipeDetailThumbnail = (ImageView)view.findViewById(R.id.recipeDetailThumbnail);
        detailName = (TextView)view.findViewById(R.id.detailName);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "Aller_Bd.ttf");
        detailName.setTypeface(font);
        Typeface font2 = Typeface.createFromAsset(getContext().getAssets(), "Aller_Rg.ttf");

        calories = (TextView)view.findViewById(R.id.calories);
        sizeUnit = (TextView)view.findViewById(R.id.sizeUnit);
        enterNumber = (EditText)view.findViewById(R.id.editText);
        addFood = (Button)view.findViewById(R.id.addFood);
        calFat = (TextView)view.findViewById(R.id.calFat);
        carbs = (TextView)view.findViewById(R.id.carbs);
        fiber = (TextView)view.findViewById(R.id.fiber);
        sugar = (TextView)view.findViewById(R.id.sugar);
        sodium = (TextView)view.findViewById(R.id.sodium);

        mContext = getActivity();

        calories.setTypeface(font2);
        sizeUnit.setTypeface(font2);
        enterNumber.setTypeface(font2);
        calFat.setTypeface(font2);

        addFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                edit = Integer.parseInt(enterNumber.getText().toString());
                cals_consumed = item.getFields().getNf_calories() * edit;
                cals_consumed += CalorieConsumed.getCalorie(mContext);
                CalorieConsumed.setCalorie(mContext, cals_consumed);

                //loadItemsForCategory(products.get(position).getName().toString());
                Toast.makeText(getContext(), "Calories Added!", Toast.LENGTH_SHORT).show();

            }


        });



        detailName.setText(item.getFields().getItem_name());
        calories.setText("Calories per item: "  + item.getFields().get_calories());
        sizeUnit.setText("Serving size: "  + item.getFields().getNf_serving_size_qty() + " "
                + item.getFields().getNf_serving_size_unit());
        calFat.setText("Calories from fat: " + item.getFields().getNf_calories_from_fat());
        carbs.setText("Carbs: " + item.getFields().getNf_total_carbohydrate());
        fiber.setText("Fiber: " + item.getFields().getNf_dietary_fiber());
        sugar.setText("Sugar: " + item.getFields().getNf_sugars());
        sodium.setText("Sodium: " + item.getFields().getNf_sodium());
        return view;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }
}
