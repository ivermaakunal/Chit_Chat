package com.example.whatsapp1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.example.whatsapp1.databinding.ActivityChatBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class chatActivity extends AppCompatActivity {

    ActivityChatBinding bind;
    FirebaseAuth auth;
    FirebaseDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = ActivityChatBinding.inflate(getLayoutInflater());
        setContentView(bind.getRoot());

        db = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String  snederid = auth.getUid();
        String reciverid= getIntent().getStringExtra("rid");
        String imguri = getIntent().getStringExtra("imguri");
        String username = getIntent().getStringExtra("name");

        Picasso.get().load(imguri).placeholder(R.drawable.baseline_person_24).into(bind.usImg);
        bind.usrnmx.setText(username);



        final ArrayList<messageModel> msgModel = new ArrayList<>();
        final chatAdapter ad = new chatAdapter(msgModel,this,reciverid);

        bind.chatRecycle.setAdapter(ad);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        bind.chatRecycle.setLayoutManager(layoutManager);

        final String sender_room = snederid + reciverid;
        final String reciverRoom = reciverid + snederid;

        bind.sendtxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String message = bind.chatxy.getText().toString();
                final messageModel model = new messageModel(snederid,message);
                model.setTime(new Date().getTime());
                bind.chatxy.setText(",");
            }
        });
        
    }
}