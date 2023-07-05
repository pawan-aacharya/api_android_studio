package com.example.firstapiexample;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private Context context;
    private ArrayList<UserList> list;
    public MyAdapter(Context context, ArrayList<UserList> list){
        this.context=context;
        this.list=list;

    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(context);
        View view=inflater.inflate(R.layout.row_item_layout,parent,false);
        MyViewHolder holder=new MyViewHolder(view);
       return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        UserList user=list.get(position);
        holder.txtUserId.setText(String.valueOf(user.userId));
        holder.txtUserName.setText(user.userName);
        holder.txtUserPassword.setText(user.userPassword);
        holder.txtUserRole.setText(user.userRole);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView txtUserId,txtUserName,txtUserPassword,txtUserRole;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUserId=itemView.findViewById((R.id.txtUserId));
            txtUserName=itemView.findViewById(R.id.txtUserName);
            txtUserPassword=itemView.findViewById(R.id.txtUserPassword);
            txtUserRole=itemView.findViewById(R.id.txtUserRole);
        }
    }
}
