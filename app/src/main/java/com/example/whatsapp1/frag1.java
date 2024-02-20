package com.example.whatsapp1;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.whatsapp1.databinding.FragmentFrag1Binding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class frag1 extends Fragment {

    public frag1(){

    }
    FragmentFrag1Binding bind;
    ArrayList<usrModel> list= new ArrayList<>();
    FirebaseDatabase datab;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        bind = FragmentFrag1Binding.inflate(inflater,container,false);
        datab = FirebaseDatabase.getInstance();

        recycleAdapter adapter = new recycleAdapter(list,getContext());
        bind.recycleview.setAdapter(adapter);

        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        bind.recycleview.setLayoutManager(manager);

        datab.getReference().child("uploads").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    usrModel usr = dataSnapshot.getValue(usrModel.class);

                    list.add(usr);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return bind.getRoot();

    }
}