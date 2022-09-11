package com.example.petit_forkt;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.petit_forkt.Prevalent.Prevalent;
import com.example.petit_forkt.model.Users;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;


public class RegistrationActivity extends AppCompatActivity {
Button loginPage,register;
EditText phone,email1,confirmPassword,password;
String emailPattern="^(.+)@(\\S+)$";
ProgressDialog progressDialog;

FirebaseAuth mAuth;
FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        loginPage=findViewById(R.id.loginPage);
        register=findViewById(R.id.Register);

        phone=findViewById(R.id.phone);
        email1=findViewById(R.id.email);
        confirmPassword=findViewById(R.id.confirmPassword);
        password=findViewById(R.id.password);

        progressDialog=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        mUser=mAuth.getCurrentUser();

        loginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformAuth();
            }
        });
    }

    private void PerformAuth(){
        String emailRegister=email1.getText().toString();
        String passwordRegister=password.getText().toString();
        String confPassRegister=confirmPassword.getText().toString();
        String phoneRegister=phone.getText().toString();

        if(!emailRegister.matches(emailPattern)){
            Toast.makeText(RegistrationActivity.this,"Give legit Email ID",Toast.LENGTH_SHORT).show();
        }
        else if(passwordRegister.isEmpty() || passwordRegister.length()<6)
        {
            Toast.makeText(RegistrationActivity.this,"Password must be atleast 6 characters long",Toast.LENGTH_SHORT).show();
        }
        else if(!passwordRegister.equals(confPassRegister))
        {
            Toast.makeText(RegistrationActivity.this,"Password doesn't Match",Toast.LENGTH_SHORT).show();
        }
        else if(phoneRegister.length()!=10)
        {
            Toast.makeText(RegistrationActivity.this,"Phone Number is Incorrect",Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setMessage("Please Wait While Registration");
            progressDialog.setTitle("Registration");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            ValidatePhoneNumber(emailRegister,phoneRegister,passwordRegister);
        }

    }

    private void ValidatePhoneNumber(String emailRegister, String phoneRegister, String passwordRegister)
    {
        final DatabaseReference firebaseDatabase;
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(!(snapshot.child("Users").child(phoneRegister).exists()))
                {
                    HashMap<String,Object> userDataMap=new HashMap<>();
                    userDataMap.put("Email",emailRegister);
                    userDataMap.put("Password",passwordRegister);
                    userDataMap.put("Phone",phoneRegister);
                    userDataMap.put("userType",0);

                    firebaseDatabase.child("Users").child(phoneRegister).updateChildren(userDataMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                progressDialog.dismiss();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                        }
                    });
                }
                else
                {
                    Toast.makeText(RegistrationActivity.this,"this "+phoneRegister+" number exists ",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}