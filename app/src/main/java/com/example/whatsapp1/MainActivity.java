package com.example.whatsapp1;

import static com.example.whatsapp1.R.*;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
//    private EditText phonenumber, phcode;
//    private Button send;
    private CountryCodePicker ccode;
    private EditText phonenumber;
    private  Button x;
    String st;
    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("ChitChat");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setIcon(drawable.green_whatsapp);
        phonenumber = findViewById(id.phonenumber);
//        ccode = findViewById(R.id.ccp);
        x = findViewById(R.id.send);

//        ccode.registerCarrierNumberEditText(phonenumber);

        x.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, VerifyActivity2.class);
                st= phonenumber.getText().toString();
                i.putExtra("phone",st.replace(" ",""));
                startActivity(i);
            }
        });



    }

    protected  void onStart(){
        super.onStart();
        FirebaseUser current= FirebaseAuth.getInstance().getCurrentUser();
        if(current!=null){
            startActivity(new Intent(getApplicationContext(),MainActivity3.class));
            finish();
        }
    }

}