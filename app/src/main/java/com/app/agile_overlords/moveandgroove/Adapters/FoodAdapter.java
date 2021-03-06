package com.app.agile_overlords.moveandgroove.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.agile_overlords.moveandgroove.Models.NutritionItemModel;
import com.app.agile_overlords.moveandgroove.R;

import java.util.ArrayList;
import java.util.List;



/**
 * Created by Scott Williams on 5/5/2016.
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.MyViewHolder>{

    private ArrayList<NutritionItemModel> foodList1;
    private List<NutritionItemModel> foodList;

    public FoodAdapter(ArrayList<NutritionItemModel> nutritionItemCollection){
        this.foodList1 = nutritionItemCollection;
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView foodName, foodCalories;

        public MyViewHolder(View view) {
            super(view);
            foodName =(TextView) view.findViewById(R.id.item_name);
            foodCalories = (TextView) view.findViewById(R.id.brand_name);
            //TODO if you want to display any other fields, declare them here
        }
    }
    public  FoodAdapter(List<NutritionItemModel> foodList) {
        this.foodList = foodList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_nutrition,parent,false);
        return new MyViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position){
        NutritionItemModel foodModel = foodList.get(position);
        holder.foodName.setText(foodModel.getFields().getItem_name());
        holder.foodCalories.setText(String.valueOf(foodModel.getFields().getNf_calories()));
        //holder.foodCalories.setText(foodModel.getFields().get_calories());

    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }
}
