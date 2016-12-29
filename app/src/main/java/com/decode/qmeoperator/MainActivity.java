package com.decode.qmeoperator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

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


        DatabaseReference queueRef = FirebaseDatabase.getInstance().getReference().child("queue");
        queueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: here");
                Log.d(TAG, "onDataChange: "+dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
