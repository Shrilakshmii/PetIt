package com.example.petit_forkt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petit_forkt.Prevalent.Prevalent;
import com.example.petit_forkt.model.Cart;
import com.example.petit_forkt.model.CartViewAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    CartViewAdapter adapter;
    RecyclerView.LayoutManager layoutManager;
    Button endBtn;
    TextView tableNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerView = findViewById(R.id.cartList);
        recyclerView.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        endBtn = findViewById(R.id.endBtn);

        tableNum=findViewById(R.id.tableNum);

        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference();

        FirebaseRecyclerOptions<Cart> options = new FirebaseRecyclerOptions.Builder<Cart>().setQuery(mDatabase.child("Cart").child("Products").child(Prevalent.currentOnlineUser.getPhone()), Cart.class).build();

        adapter=new CartViewAdapter(options);
        recyclerView.setAdapter(adapter);

        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(CartActivity.this,"Food Stored Successfully",Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}
