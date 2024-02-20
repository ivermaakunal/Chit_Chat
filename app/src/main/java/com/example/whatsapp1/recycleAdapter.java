package com.example.whatsapp1;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class recycleAdapter extends RecyclerView.Adapter<recycleAdapter.ViewHolder>{

    ArrayList<usrModel> list;
    Context contextl;

    public recycleAdapter(ArrayList<usrModel> list, Context contextl) {
        this.list = list;
        this.contextl = contextl;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(contextl).inflate(R.layout.item_recycle,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        usrModel usr = list.get(position);
        Picasso.get().load(usr.getImguri()).placeholder(R.drawable.baseline_person_24).into(holder.imguri);
        holder.name.setText(usr.getName());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(contextl,chatActivity.class);
                i.putExtra("imguri",usr.getImguri());
                i.putExtra("name",usr.getName());
                contextl.startActivity(i);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imguri;
        TextView name,phnum;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imguri = itemView.findViewById(R.id.imgV);
            name = itemView.findViewById(R.id.namev);
            phnum = itemView.findViewById(R.id.msgV);
        }



    }
}
