package com.example.petit_forkt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {
    Button RegisterPage,login;
    EditText phoneLogin,passwordLogin;
    String emailPattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    //SharedPreferences sharedPreferences;

    //private static final String SHARED_PREF_NAME="my_pref";
    //private static final String KEY_NAME="name";
    //private static final String KEY_EMAIL="email";

    ProgressDialog progressDialog;
    FirebaseAuth mAuth;
    FirebaseUser mUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RegisterPage = findViewById(R.id.registerPage);
        login = findViewById(R.id.btnLogin);
        phoneLogin = findViewById(R.id.phoneLogin);
        passwordLogin = findViewById(R.id.passwordLogin);

        progressDialog = new ProgressDialog(this);
        mAuth = FirebaseAuth.getInstance();
        mUser = mAuth.getCurrentUser();
        //sharedPreferences=getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PerformLogin();
            }
        });
        RegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
            }
        });
    }
    private void PerformLogin() {
        String phoneRegister = phoneLogin.getText().toString();
        String passwordRegister= passwordLogin.getText().toString();

        if (passwordRegister.isEmpty() || passwordRegister.length() < 6)
        {
            Toast.makeText(MainActivity.this, "Password must be atleast 6 characters long", Toast.LENGTH_SHORT).show();
        }
        else
        {
            progressDialog.setMessage("Please Wait While Login");
            progressDialog.setTitle("Login Done");
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();

            AllowAccessToNextAccount(phoneRegister,passwordRegister);
        }
    }

    private void AllowAccessToNextAccount(String phoneRegister, String passwordRegister)
    {
        final DatabaseReference firebaseDatabase;
        firebaseDatabase=FirebaseDatabase.getInstance().getReference();
        firebaseDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.child("Users").child(phoneRegister).exists())
                {
                        Users userData=snapshot.child("Users").child(phoneRegister).getValue(Users.class);
                        if(userData.getPhone().equals(phoneRegister))
                        {
                            if(userData.getPassword().equals(passwordRegister))
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                                phoneLogin.setEnabled(false);
                                passwordLogin.setEnabled(false);

                                if (userData.getUserType()==0){
                                    sendUserToNextActivity();
                                    Prevalent.currentOnlineUser=userData;
                                }
                                if(userData.getUserType()==1){
                                    sendUserToNextActivity1();
                                }
                            }
                            else
                            {
                                progressDialog.dismiss();
                                Toast.makeText(MainActivity.this,"Password is invalid",Toast.LENGTH_SHORT).show();
                            }
                        }
                }
                else
                {
                    Toast.makeText(MainActivity.this,"this "+phoneRegister+" number does not exist ",Toast.LENGTH_SHORT).show();
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private void sendUserToNextActivity()
    {
        Intent intent=new Intent(MainActivity.this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
    private void sendUserToNextActivity1()
    {
        Intent intent=new Intent(MainActivity.this,AdminHomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }
}