package com.example.lnthe54.webservice.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lnthe54.webservice.R;
import com.example.lnthe54.webservice.model.Food;

import java.util.ArrayList;

/**
 * @author lnthe54 on 10/22/2018
 * @project WebService
 */
public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {

    private ArrayList<Food> listFood;
    private CallBack callBack;

    public FoodAdapter(CallBack callBack, ArrayList<Food> listFood) {
        this.callBack = callBack;
        this.listFood = listFood;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View rows = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list_food, parent, false);
        return new ViewHolder(rows);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Food food = listFood.get(position);
        viewHolder.bindData(food);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callBack.itemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFood.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivFood;
        private TextView tvFoodName;
        private TextView tvFoodPrice;
        private TextView tvFoodProducts;
        private TextView tvFoodTime;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivFood = itemView.findViewById(R.id.iv_food);
            tvFoodName = itemView.findViewById(R.id.tv_name_food);
            tvFoodPrice = itemView.findViewById(R.id.tv_price);
            tvFoodProducts = itemView.findViewById(R.id.tv_products);
            tvFoodTime = itemView.findViewById(R.id.tv_time);
        }

        public void bindData(Food food) {

            Glide.with(itemView.getContext()).load(food.getFoodImg()).into(ivFood);

            tvFoodName.setText(food.getFoodName());
            tvFoodPrice.setText(food.getFoodPrice() + "K");
            tvFoodProducts.setText(food.getAddress());
            tvFoodTime.setText(food.getTime());
        }
    }

    public interface CallBack {
        void itemClick(int position);
    }
}
