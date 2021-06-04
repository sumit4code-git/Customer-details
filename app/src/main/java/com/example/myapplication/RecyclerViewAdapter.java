package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    private List<Customer_Model> list;

    public RecyclerViewAdapter(Context context, List<Customer_Model> list) {
        this.context = context;
        this.list = list;
    }
//this will create Viewholder for each data ,and its code remain same for all cases
    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }
//attaching data to each card view
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
    Customer_Model cm=list.get(position);
    holder.name.setText(cm.getName());
    holder.id.setText(Integer.toString(cm.getId()));
    holder.age.setText(Integer.toString(cm.getAge()));
    holder.icon.setImageResource(R.drawable.download);
    holder.sw.setText(Boolean.toString(cm. isIsactive()));
    }
// This return size of recylerview which will shown on current screen
    @Override
    public int getItemCount() {
        return list.size();
    }
//    To acknowledge each detail in row XML here in recyclerview
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public TextView id;
        public TextView name;
        public TextView age;
        public ImageView icon;
        public TextView sw;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            id=itemView.findViewById(R.id.idtextview);
            name=itemView.findViewById(R.id.nameTextViiew);
            age=itemView.findViewById(R.id.AgetextView);
            icon=itemView.findViewById(R.id.imageView);
            sw=itemView.findViewById((R.id.switchTextView));
        }
        public void onClick(View view){
            int position=this.getAdapterPosition();/*These two line will create object of cutomer_Model class where click is done*/
            Customer_Model cm=list.get(position);
            int age=cm.getAge();
            int id=cm.getId();
            String name=cm.getName();
            boolean ac=cm.isIsactive();
            Intent intent =new Intent(context,Display.class);
            intent.putExtra("Rname",name);
            intent.putExtra("Rage",age);
            intent.putExtra("Rid",id);
            intent.putExtra("Rstatus",ac);
            intent.putExtra("Rpos",position);
            context.startActivity(intent);
        }
    }
}
