package com.example.whatsapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class VerifyActivity2 extends AppCompatActivity {

    TextView re;
    EditText enter_otp;
    Button verify;

    FirebaseAuth mAuth;
    String phNumber,otpcode,mVerificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify2);
        getSupportActionBar().setTitle("ChitChat");
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        enter_otp= findViewById(R.id.otp);
        verify = findViewById(R.id.verify_otp);
        re = findViewById(R.id.resend);
        phNumber = getIntent().getStringExtra("phone");
        mAuth = FirebaseAuth.getInstance();

        String number= phNumber.toString();
        generate_otp(number);

        verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verifycode(enter_otp.getText().toString());
//                if(enter_otp.getText().toString().length()!=6) {
//                    Toast.makeText(getApplicationContext(), "OTP Invalid", Toast.LENGTH_SHORT).show();
//                }
//                else if(enter_otp.getText().toString().isEmpty()) {
//                    Toast.makeText(getApplicationContext(), "OTP Field Can;t be Empty", Toast.LENGTH_SHORT).show();
//                }
//                else{
//                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpcode, enter_otp.getText().toString());
//                    signInWithPhoneAuthCredential(credential);
//                }

            }
        });




        re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(VerifyActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });





    }


    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
    mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {
            final String code = credential.getSmsCode();
            if(code!=null){
                verifycode(code);
            }
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException e) {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onCodeSent(@NonNull String s,
                @NonNull PhoneAuthProvider.ForceResendingToken token) {
            super.onCodeSent(s, token);
            mVerificationId = s;

        }
    };
    private void generate_otp(String phNumber) {
//        PhoneAuthProvider.getInstance().verifyPhoneNumber(phNumber,60, TimeUnit.SECONDS,this,mCallBacks);

        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber("+91"+phNumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // (optional) Activity for callback binding
                        // If no activity is passed, reCAPTCHA verification can not be used.
                        .setCallbacks(mCallbacks)          // OnVerificationStateChangedCallbacks
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);
    }


    private void verifycode(String code){

        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId,code);
        signInByCred(credential);

    }

    private void signInByCred(PhoneAuthCredential credential) {
        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), form.class));
                            finish();
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "CODE ERROR", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

//    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
//        mAuth.signInWithCredential(credential)
//                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()) {
//                            startActivity(new Intent(VerifyActivity2.this, MainActivity3.class));
//                            finish();
//                        }
//                        else {
//                            Toast.makeText(getApplicationContext(), "CODE ERROR", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                });
//    }
}