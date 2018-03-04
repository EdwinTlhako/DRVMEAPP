package com.example.blaq.drvme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity {

    private Button cont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        cont = findViewById(R.id.btn_continueReg);
    }

    public void contReg(View view)
    {
        startActivity(new Intent(RegisterActivity.this,LogPaymentInfomationActivity.class));
    }
}
