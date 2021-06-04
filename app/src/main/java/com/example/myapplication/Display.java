package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
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
                boolean deleteone = db.deleteone(cm);
                Toast.makeText(Display.this, "Sucessfully deleted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}