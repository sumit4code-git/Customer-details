package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLOUMN_ID = "ID";
    public static final String COLOUMN_CUSTOMER_NAME = "CUSTOMER_NAME";
    public static final String COLOUMN_CUSTOMER_AGE = "CUSTOMER_AGE";
    public static final String COLOUMN_ACTIVE_CUSTOMER = "ACTIVE_CUSTOMER";

    public DatabaseHelper(@Nullable Context context) {
        super(context,"customer.db",null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
         String createTableStatement= "CREATE TABLE " + CUSTOMER_TABLE + "(" + COLOUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COLOUMN_CUSTOMER_NAME + " TEXT," + COLOUMN_CUSTOMER_AGE + " INT," + COLOUMN_ACTIVE_CUSTOMER + " BOOL )";
         db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    public boolean addone(Customer_Model cm){
        SQLiteDatabase data=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(COLOUMN_CUSTOMER_NAME,cm.getName());
        cv.put(COLOUMN_CUSTOMER_AGE,cm.getAge());
        cv.put(COLOUMN_ACTIVE_CUSTOMER,cm.isIsactive());
        long insert = data.insert(CUSTOMER_TABLE, null, cv);
        if(insert==-1){
            return false;
        }
        else{
            return true;
        }
    }
    public List<Customer_Model> getEveryone(){
        List<Customer_Model> retunlist= new ArrayList<>();
        String query ="SELECT * FROM "+CUSTOMER_TABLE;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int count = cursor.getCount();/*this count function will return total number  of rows in database*/
        if(cursor.moveToFirst()){
            do{
                int customer_Id=cursor.getInt(0);
                String customer_name=cursor.getString(1);
                int customer_age=cursor.getInt(2);
                boolean isActive=cursor.getInt(3)==1?true:false;
                Customer_Model cm=new Customer_Model(customer_Id,customer_name,customer_age,isActive);
                retunlist.add(cm);/*here Tostring function is ued to add */
            }while(cursor.moveToNext());
        }
        else{

        }
        cursor.close();
        db.close();
        return retunlist;
    }
    public boolean deleteone(Customer_Model cm){
        SQLiteDatabase sq=this.getWritableDatabase();
        String query="DELETE FROM "+CUSTOMER_TABLE+" WHERE "+COLOUMN_ID+"="+cm.getId();
        Cursor cursor = sq.rawQuery(query, null);
        if(cursor.moveToFirst()){
            return true;
        }
        else{
            return false;
        }
    }
}
