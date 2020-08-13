package com.actvn.shopapp.search;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.actvn.shopapp.R;
import com.actvn.shopapp.api.model.Data;
import com.actvn.shopapp.api.model.Description;
import com.bumptech.glide.Glide;


import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.MyViewHolder> {
    public static final String BASE_URL = "http://app.baomoiday.net/public/";

    private Context context;
    private List<Data> datas;
    private List<Description> descriptions = new ArrayList<>();

    public SearchAdapter(Context context, List<Data> datas) {
        this.context = context;
        this.datas = datas;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Data data = datas.get(position);

        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.txtTitle.setText(String.valueOf(data.getDescriptions().get(1).getName()));
        holder.txtCost.setText(String.valueOf(decimalFormat.format(data.getCost())+ " đ"));

        Glide.with(context).load(String.valueOf(BASE_URL + data.getImage()))
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_launcher)
                .into(holder.img);

    }

    @Override
    public int getItemCount() {
        return datas.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txtTitle;
        TextView txtCost;
        ImageView img;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtCost = itemView.findViewById(R.id.txtCost);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    /*Intent intent = new Intent(context, DetailActivity.class);
                    context.startActivity(intent);*/
                }
            });


        }

    }
}
