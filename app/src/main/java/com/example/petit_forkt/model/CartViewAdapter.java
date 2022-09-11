package com.example.petit_forkt.model;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petit_forkt.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

public class CartViewAdapter extends FirebaseRecyclerAdapter<Cart, CartViewAdapter.cartViewHolder>
{
    public CartViewAdapter(@NonNull FirebaseRecyclerOptions<Cart> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull cartViewHolder holder, int position, @NonNull Cart model) {
        holder.txtfName.setText(model.getFoodtitle());
        holder.txtfPrice.setText(model.getFoodprice());
        holder.txtfQuantity.setText(model.getFoodquantity());
    }

    @NonNull
    @Override
    public cartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_items_layout, parent, false);
        return new cartViewHolder(view);
    }

    public static final class cartViewHolder extends RecyclerView.ViewHolder {
        TextView txtfName, txtfPrice, txtfQuantity;

        public cartViewHolder(@NonNull View itemView) {
            super(itemView);
            txtfName = itemView.findViewById(R.id.endFoodName);
            txtfPrice = itemView.findViewById(R.id.endFoodPrice);
            txtfQuantity = itemView.findViewById(R.id.endFoodQuantity);
        }
    }
}
