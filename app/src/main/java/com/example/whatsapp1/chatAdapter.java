package com.example.whatsapp1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class chatAdapter extends RecyclerView.Adapter {




    ArrayList<messageModel> msgmod;
    Context context;
    String reciverid;

    int sender_view=1;
    int reciever_view=2;

    public chatAdapter(ArrayList<messageModel> msgmod, Context context, String reciverid) {
        this.msgmod = msgmod;
        this.context = context;
        this.reciverid = reciverid;
    }

    public chatAdapter(ArrayList<messageModel> msgmod, Context context) {
        this.msgmod = msgmod;
        this.context = context;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==sender_view){
            View view = LayoutInflater.from(context).inflate(R.layout.sender,parent,false);
            return new SenderViewHolder(view);
        }
        else{
            View view = LayoutInflater.from(context).inflate(R.layout.reciever,parent,false);
            return  new ReciverViewHolder(view);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if(msgmod.get(position).getClass().equals(FirebaseAuth.getInstance().getUid())){
            return sender_view;
        }
        else {
            return reciever_view;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        messageModel model= msgmod.get(position);
        if(holder.getClass() == SenderViewHolder.class){
            ((SenderViewHolder)holder).smsg.setText(model.getMessage());
        }
        else{
            ((ReciverViewHolder)holder).rmsg.setText(model.getMessage());
        }

    }

    @Override
    public int getItemCount() {
        return msgmod.size();
    }

    public class ReciverViewHolder extends RecyclerView.ViewHolder{

        TextView rmsg,rtime;
        public ReciverViewHolder(@NonNull View itemView) {
            super(itemView);
            rmsg = itemView.findViewById(R.id.rctext);
            rtime = itemView.findViewById(R.id.rtime);
        }
    }

    public class SenderViewHolder extends RecyclerView.ViewHolder{
        TextView smsg,stime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            smsg = itemView.findViewById(R.id.sendtxt);
            stime= itemView.findViewById(R.id.sendertime);
        }
    }
}
