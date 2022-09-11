package com.example.petit_forkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.petit_forkt.Prevalent.Prevalent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.DatabaseReference;

import java.util.HashMap;


public class HomeActivity extends AppCompatActivity {

    TextView table1,table2,table3,table4,name;
    Button btnTable1,btnTable2,btnTable3,btnTable4,home,aboutus,contactus,thankyou,cart;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        table1=findViewById(R.id.table1);
        table2=findViewById(R.id.table2);
        table3=findViewById(R.id.table3);
        table4=findViewById(R.id.table4);
        name=findViewById(R.id.name);
        mDatabase=FirebaseDatabase.getInstance().getReference();

        btnTable1=findViewById(R.id.btnTable1);
        btnTable2=findViewById(R.id.btnTable2);
        btnTable3=findViewById(R.id.btnTable3);
        btnTable4=findViewById(R.id.btnTable4);
        home=findViewById(R.id.home);
        aboutus=findViewById(R.id.aboutus);
        cart=findViewById(R.id.cart);
        contactus=findViewById(R.id.contactus);
        thankyou=findViewById(R.id.thankyou);

        String name1=Prevalent.currentOnlineUser.getEmail();
        name.setText(name1);

        btnTable1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Table").child(Prevalent.currentOnlineUser.getPhone()).setValue(1).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this,"Table1 Is Added",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"Error Adding Table",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent=new Intent(HomeActivity.this,foodActivity.class);
                startActivity(intent);

            }
        });
        btnTable2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mDatabase.child("Table").child(Prevalent.currentOnlineUser.getPhone()).setValue(2).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this,"Table2 Is Added",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"Error Adding Table",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent=new Intent(HomeActivity.this,foodActivity.class);
                startActivity(intent);

            }
        });
        btnTable3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Table").child(Prevalent.currentOnlineUser.getPhone()).setValue(3).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this,"Table3 Is Added",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"Error Adding Table",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent=new Intent(HomeActivity.this,foodActivity.class);
                startActivity(intent);
            }
        });
        btnTable4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDatabase.child("Table").child(Prevalent.currentOnlineUser.getPhone()).setValue(4).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(HomeActivity.this,"Table4 Is Added",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(HomeActivity.this,"Error Adding Table",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                Intent intent=new Intent(HomeActivity.this,foodActivity.class);
                startActivity(intent);

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,HomeActivity.class);
                startActivity(intent);
            }
        });
        aboutus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this,AboutUsActivity.class);
                startActivity(intent);
            }
        });
        thankyou.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ThankyouActivity.class);
                startActivity(intent);
            }
        });
        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, ContactUsActivity.class);
                startActivity(intent);
            }
        });
        cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(HomeActivity.this, CartActivity.class);
                startActivity(intent);
            }
        });
    }
}


