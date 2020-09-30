package com.example.erestaurant;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword, mAge, mFavFood, mUserKey;
    Button mRegisterBtn;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    //collecting data
    DatabaseReference reff, reff2;
    MemberInfo member;
    String currentUserId, User;
    //end

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFullName = findViewById(R.id.fullName);
        mEmail = findViewById(R.id.Email);
        mPassword = findViewById(R.id.Password);
        mRegisterBtn = findViewById(R.id.registerBtn);
        mLoginBtn = findViewById(R.id.createText);
        mUserKey = findViewById(R.id.staffID);

        mAge = findViewById(R.id.age);
        mFavFood = findViewById(R.id.favFood);
        fAuth = FirebaseAuth.getInstance();
        //currentUserId= fAuth.getCurrentUser().getUid();
      //  final boolean[] myUser = {false};


        //collecting data
        member=new MemberInfo();
        reff = FirebaseDatabase.getInstance().getReference().child("MemberInfo");
        reff2 = FirebaseDatabase.getInstance().getReference().child("StaffKeys");

      // end


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String favfood = mFavFood.getText().toString().trim();
                final int agea= Integer.parseInt(mAge.getText().toString().trim());
                final String key = mUserKey.getText().toString().trim();

                reff2.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String mykey = null;
                        if(dataSnapshot.exists()) {
                            mykey = dataSnapshot.child("staffKey").getValue().toString();
                            if(!key.equals(mykey)) {
                             //   myUser[0] = true;
                                member.setUserKey("User");
                               // finish();
                            } else {
                                member.setUserKey(mykey);
                                reff2.child("staffKey").removeValue();
                               // finish();
                            }
                        } else if(member.getUserKey() == null) {
                            member.setUserKey("User");
                           // finish();
                        }



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        //Toast.makeText(this, databaseError.getCode(), Toast.LENGTH_SHORT.show();
                        Toast.makeText(Register.this, "unable", Toast.LENGTH_SHORT).show();
                    }
                });


                if(TextUtils.isEmpty(email)) {
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)) {
                    mPassword.setError("Password is Required.");
                    return;
                }
                if(password.length() < 6) {
                    mPassword.setError("Password must be longer");
                    return;
                }

                //register a user
                fAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Toast.makeText(Register.this, "User Created", Toast.LENGTH_SHORT).show();

                            member.setFullname(mFullName.getText().toString().trim());
                            member.setAge(agea);
                            member.setEmail(email);
                            member.setFavFood(favfood);
                            currentUserId= fAuth.getCurrentUser().getUid();
                            reff.child(currentUserId).setValue(member);


                            startActivity(new Intent(getApplicationContext(), Login.class));
                        } else {
                            Toast.makeText(Register.this, "Error! " + task.getException(),Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });



    }
}