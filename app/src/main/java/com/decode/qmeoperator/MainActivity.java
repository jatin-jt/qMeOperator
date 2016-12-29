package com.decode.qmeoperator;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.decode.qmeoperator.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    DatabaseReference queueRef;
    String uid;
    String length;
    String firstid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        final TextView queueLength = (TextView) findViewById(R.id.queueLengthText);
        final TextView customerId = (TextView) findViewById(R.id.currentCustomerText);
        final TextView customerName = (TextView) findViewById(R.id.customerNameText);
        final TextView customerEmail = (TextView) findViewById(R.id.emailText);
        final TextView customerPhone = (TextView) findViewById(R.id.phoneText);
        final TextView customerAccountId = (TextView) findViewById(R.id.accountIdText);

        Button nextCustomer = (Button) findViewById(R.id.callNextButton);


        queueRef = FirebaseDatabase.getInstance().getReference().child("queue").child("0x0117c555c65f");
        //Query queryRef = queueRef.child("users").orderByValue()
        queueRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                length = dataSnapshot.child("length").getValue().toString();
                queueLength.setText(dataSnapshot.child("length").getValue().toString());
                firstid = dataSnapshot.child("firstuser").getValue().toString();
                customerId.setText(dataSnapshot.child("firstuser").getValue().toString());
                boolean flag = false;
                DataSnapshot queueUsers = dataSnapshot.child("users");
                Log.d(TAG, "onDataChange: " + queueUsers);
                for (DataSnapshot qUser : queueUsers.getChildren()) {

                    String str = qUser.getValue(Long.class).toString();
                    uid = qUser.getKey();
                    if (str.equals(firstid)) {
                        FirebaseDatabase.getInstance().getReference().child("users").child(uid).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot1) {
                                Log.d(TAG, "onDataChange: 1" + dataSnapshot1);
                                User currentUser = dataSnapshot1.getValue(User.class);
                                customerName.setText(currentUser.getName());
                                customerEmail.setText(currentUser.getEmailId());
                                customerPhone.setText(currentUser.getContactNo());
                                customerAccountId.setText(currentUser.getAccountNo());

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                        break;
                    }
                }

                System.out.print(dataSnapshot);
                Log.d(TAG, "onDataChange: here");
                Log.d(TAG, "onDataChange: " + dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        nextCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                queueRef.child("users").child(uid).removeValue();
                int l = Integer.parseInt(length);
                l--;
                int f = Integer.parseInt(firstid);
                f++;
                queueRef.child("length").setValue(l);
                queueRef.child("firstuser").setValue(f).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(MainActivity.this, "Now servicing next customer", Toast.LENGTH_LONG).show();

                        Intent i = new Intent(MainActivity.this, MainActivity.class);
                        startActivity(i);


                    }
                });



            }
        });

    }
}
