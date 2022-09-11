package com.example.petit_forkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petit_forkt.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class PulavActivity extends AppCompatActivity {

    TextView foodtitle,foodprice,foodtaste,count;
    ImageView foodimage;
    Button gotocart,addtocart;
    ImageView plus,minus;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pulav);
        gotocart=findViewById(R.id.gotocart);
        addtocart=findViewById(R.id.addtocart);
        count=findViewById(R.id.count);
        plus=findViewById(R.id.plus);
        minus=findViewById(R.id.minus);


        gotocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(PulavActivity.this, CartActivity.class);
                startActivity(intent);

            }
        });

        foodtitle=findViewById(R.id.foodtitle);
        foodtaste=findViewById(R.id.foodtaste);
        foodprice=findViewById(R.id.foodprice);
        foodimage=findViewById(R.id.foodimage);

        foodtitle.setText(getIntent().getExtras().getString("foodname"));
        foodprice.setText(getIntent().getExtras().getString("foodprice"));

        int imageId=getIntent().getIntExtra("foodimage",0);
        foodimage.setImageResource(imageId);

        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addingToCartList();
            }
        });
        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count1= count.getText().toString();
                int count2 = Integer.parseInt(count1);
                count2=count2+1;
                count.setText(String.valueOf(count2));
            }
        });
        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String count1= count.getText().toString();
                int count2 = Integer.parseInt(count1);

                if(count2==1) {
                    Toast.makeText(PulavActivity.this,"Cannot decrement more",Toast.LENGTH_SHORT).show();
                }
                else {
                    count2=count2-1;
                    count.setText(String.valueOf(count2));

                }
            }
        });

    }
    private void addingToCartList()
    {
        mDatabase= FirebaseDatabase.getInstance().getReference().child("Cart");
        HashMap<String,Object>cartMap=new HashMap<>();
        cartMap.put("foodtitle",foodtitle.getText().toString());
        cartMap.put("foodprice",foodprice.getText().toString());
        cartMap.put("foodquantity",count.getText().toString());

        mDatabase.child("Products").child(Prevalent.currentOnlineUser.getPhone()).push().setValue(cartMap).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(PulavActivity.this,"Added to Food Cart",Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(PulavActivity.this,foodActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}