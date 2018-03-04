package com.example.blaq.drvme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    private EditText et_Email,et_Pass;
    private Button btn_login,btn_Register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_Email = findViewById(R.id.et_login_email);
        et_Pass = findViewById(R.id.et_login_pass);

        btn_login = findViewById(R.id.btn_login);
        btn_Register = findViewById(R.id.btn_register);
    }

    public void goToRegister(View view)
    {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
    }
}
