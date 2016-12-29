package com.decode.qmeoperator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        TextView queueLength = (TextView) findViewById(R.id.queueLengthText);
        TextView customerId = (TextView) findViewById(R.id.currentCustomerText);
        TextView customerName = (TextView) findViewById(R.id.customerNameText);
        TextView customerEmail = (TextView) findViewById(R.id.emailText);
        TextView customerPhone = (TextView) findViewById(R.id.phoneText);
        TextView customerAccountId = (TextView) findViewById(R.id.accountIdText);

        Button nextCustomer = (Button) findViewById(R.id.callNextButton);

    }
}
