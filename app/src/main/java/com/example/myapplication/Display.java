package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Display extends AppCompatActivity {

    public static Activity fa;

    @Override
    protected void  onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        fa=this;
        TextView name=findViewById(R.id.Name);
        TextView age=findViewById(R.id.Age);
        TextView status=findViewById(R.id.Status);
        TextView id=findViewById(R.id.Id);
        Button but=findViewById(R.id.delete);
        Intent intent=getIntent();
        String Name=intent.getStringExtra("Rname");
        int Age=intent.getIntExtra("Rage",0);
        int Id=intent.getIntExtra("Rid",0);
        boolean Status=intent.getBooleanExtra("Rstatus",false);
        int position=intent.getIntExtra("Rpos",0);


        Customer_Model cm=new Customer_Model(Id,Name,Age,Status);
        DatabaseHelper db=new DatabaseHelper(Display.this);
        name.setText("Name is-"+Name);
        age.setText("Age is-"+Age);
        status.setText("Status is-"+Boolean.toString(Status));
        id.setText("Id is-"+Id);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogBox dilog=new DialogBox();
                Bundle bundle=new Bundle();
                bundle.putString("name",Name);
                bundle.putInt("key",Id);
                bundle.putInt("Age",Age);
                bundle.putBoolean("status",Status);
                dilog.setArguments(bundle);
                dilog.show(getSupportFragmentManager(),"Delete dialog");

            }
        });

    }
}