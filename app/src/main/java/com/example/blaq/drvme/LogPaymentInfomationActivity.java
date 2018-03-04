package com.example.blaq.drvme;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class LogPaymentInfomationActivity extends AppCompatActivity {

    private RadioGroup rg;
    private RadioButton rb;

    private Button submit,skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_payment_infomation);

        rg = findViewById(R.id.rgroup_cardtype);
        submit = findViewById(R.id.btn_submitReg);
        skip = findViewById(R.id.btn_skipCardInfo);
    }

    // determine the card type by selection
    public void getCardType(View view)
    {
        int typeByID = rg.getCheckedRadioButtonId();
        rb = findViewById(typeByID);
    }

    public void goToHome(View view)
    {
        startActivity(new Intent(getBaseContext(),HomeActivity.class));
    }


}
