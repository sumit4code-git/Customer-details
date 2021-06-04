package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        EditText et_name,et_age;
        Button btn_view,btn_add;
        Switch is_active;
        RecyclerView recyclerView;
        RecyclerViewAdapter recyclerViewAdapter;
    DatabaseHelper db=new DatabaseHelper(MainActivity.this);
//  when we get back from a display intent main activity should be refreshed so we need this constructor
    @Override
    protected void onRestart() {
        super.onRestart();
        this.recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.et_name);
        et_age=findViewById(R.id.et_age);
        btn_add=findViewById(R.id.btn_aadd);
        btn_view=findViewById(R.id.btn_view);
        is_active=findViewById(R.id.sw_active);
        recyclerView=findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        ShowCustomerOnListview(db);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Customer_Model customerModel = new Customer_Model(-1, et_name.getText().toString(), Integer.parseInt(et_age.getText().toString()), is_active.isChecked());
                    DatabaseHelper datahelp= new DatabaseHelper(MainActivity.this);
                    datahelp.addone(customerModel);
                    Toast.makeText(MainActivity.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    ShowCustomerOnListview(db);
                }catch (Exception e){
                    Toast.makeText(MainActivity.this, "Age should be Integer", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowCustomerOnListview(db);
            }
        });
    }
//    Display data in recyclerview whenever called
    public void ShowCustomerOnListview(DatabaseHelper db) {
        recyclerViewAdapter=new RecyclerViewAdapter(MainActivity.this,db.getEveryone());
        recyclerView.setAdapter(recyclerViewAdapter);
    }
}