package com.example.whatsapp1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.whatsapp1.databinding.ActivityMainBinding;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity3 extends AppCompatActivity {


    private TabLayout tb;
    private ViewPager vp;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);
        auth = FirebaseAuth.getInstance();

        tb = findViewById(R.id.tabL);
        vp = findViewById(R.id.viewpage);

        vp.setAdapter(new fragAdapter(getSupportFragmentManager()));
        tb.setupWithViewPager(vp);




    }
    @Override
    public boolean onCreatePanelMenu(int featureId, @NonNull Menu menu) {
        MenuInflater inf = getMenuInflater();
        inf.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){

            case R.id.setting:
                Toast.makeText(getApplicationContext(), "Settins", Toast.LENGTH_SHORT).show();
                return true;

            case R.id.logoutx:
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                finish();
                return true;


            default:
                return super.onOptionsItemSelected(item);
        }
    }
}