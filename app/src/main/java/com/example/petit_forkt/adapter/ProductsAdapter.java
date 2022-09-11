package com.example.petit_forkt.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.lang.Object;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petit_forkt.HomeActivity;
import com.example.petit_forkt.PulavActivity;
import com.example.petit_forkt.R;
import com.example.petit_forkt.model.Products;

import org.w3c.dom.Text;

import java.util.List;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductViewHolder> {
    public Products[] datalist;
    Context context;
    List<Products>productsList;

    public ProductsAdapter(Context context, List<Products> productsList) {
        this.context = context;
        this.productsList = productsList;

    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.product_row_item,parent,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Products datalist=productsList.get(position);
        holder.prodImage.setImageResource(productsList.get(position).getImageUrl());
        holder.prodName.setText(productsList.get(position).getProductName());
        holder.prodPrice.setText(productsList.get(position).getProductPrice());



        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v ) {
                Intent intent=new Intent(v.getContext(), PulavActivity.class);
                intent.putExtra("foodprice",datalist.getProductPrice());
                intent.putExtra("foodname",datalist.getProductName());
                intent.putExtra("foodimage",datalist.getImageUrl());


                Pair[] pairs=new Pair[1];
                pairs[0]=new Pair<View,String>(holder.prodImage,"image");
                ActivityOptions activityOptions=ActivityOptions.makeSceneTransitionAnimation((Activity) context,pairs);

                v.getContext().startActivity(intent,activityOptions.toBundle());
            }
        });
    }


    @Override
    public int getItemCount() {
        return productsList.size();
    }

    public static final class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView prodImage;
        TextView prodName,prodPrice;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);

            prodImage=itemView.findViewById(R.id.prod_image);
            prodName=itemView.findViewById(R.id.prod_name);
            prodPrice=itemView.findViewById(R.id.prod_price);


        }
    }
}
