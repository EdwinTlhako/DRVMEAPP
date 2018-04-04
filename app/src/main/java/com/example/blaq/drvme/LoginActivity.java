package com.example.blaq.drvme;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    private EditText et_Email,et_Pass;
    private Button btn_login,btn_Register;

    private FirebaseAuth nAuth;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        nAuth = FirebaseAuth.getInstance();

        et_Email = findViewById(R.id.et_login_email);
        et_Pass = findViewById(R.id.et_login_pass);

        btn_login = findViewById(R.id.btn_login);
        btn_Register = findViewById(R.id.btn_register);


    }

    public void login(View view)
    {
        final String email = et_Email.getText().toString();
        final  String pass = et_Pass.getText().toString();
        if(!(email.equals(null) || pass.equals(null) || email.equals("") || pass.equals("")))
        {
            nAuth.signInWithEmailAndPassword(email,pass).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if(!task.isSuccessful() )
                    {
                        Toast.makeText(LoginActivity.this, "Incorrect credentials ", Toast.LENGTH_LONG).show();
                    }else
                    {
                        String dbRef;

                        if(email.startsWith("driver"))
                        {
                            dbRef = "Drivers";
                            firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if(user!=null)
                                    {
                                        startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                                        LoginActivity.this.finish();
                                    }
                                }
                            };
                            String user_id = nAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(dbRef).child(user_id);
                            current_user_db.setValue(true);
                        }else
                        {
                            dbRef = "Customers";
                            firebaseAuthListener = new FirebaseAuth.AuthStateListener() {
                                @Override
                                public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                                    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                                    if(user!=null)
                                    {
                                        startActivity(new Intent(LoginActivity.this, LogPaymentInfomationActivity.class));
                                        LoginActivity.this.finish();
                                    }
                                }
                            };
                            String user_id = nAuth.getCurrentUser().getUid();
                            DatabaseReference current_user_db = FirebaseDatabase.getInstance().getReference().child("Users").child(dbRef).child(user_id);
                            current_user_db.setValue(true);
                        }

                    }
                }
            });
        }else
        {
            Toast.makeText(LoginActivity.this, "Please enter email and password ", Toast.LENGTH_LONG).show();
        }


    }

    public void goToRegister(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        LoginActivity.this.finish();
    }


    @Override
    protected void onStart() {
        super.onStart();
        nAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    protected void onStop() {
        super.onStop();
        nAuth.addAuthStateListener(firebaseAuthListener);
    }
}
