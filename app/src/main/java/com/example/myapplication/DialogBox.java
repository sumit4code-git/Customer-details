package com.example.myapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class DialogBox extends DialogFragment {
    String  name;
    int age,id;
    boolean stat;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        name=getArguments().getString("name");
        age=getArguments().getInt("Age");
        id=getArguments().getInt("key");
        stat=getArguments().getBoolean("status");
        Customer_Model cm=new Customer_Model(id,name,age,stat);
        DatabaseHelper db=new DatabaseHelper(getContext());
        Display dd=new Display();
        AlertDialog.Builder builder=new AlertDialog.Builder(getActivity());
        builder.setTitle("DELETE").setMessage("Are you sure!!").setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                boolean deleteone = db.deleteone(cm);
                Toast.makeText(getContext(), "Sucessfully deleted", Toast.LENGTH_SHORT).show();
                Display.fa.finish();
            }
        }).setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getContext(), "canceled", Toast.LENGTH_SHORT).show();
            }
        });

                return builder.create();
    }
}
