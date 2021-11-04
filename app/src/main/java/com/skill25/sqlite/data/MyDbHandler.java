package com.skill25.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.skill25.sqlite.models.Contacts;
import com.skill25.sqlite.params.params;

import java.util.ArrayList;
import java.util.List;

public class MyDbHandler extends SQLiteOpenHelper {

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }


    public void addContacts(Contacts contacts){
        SQLiteDatabase db =  this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(params.KEY_Name,contacts.getName());
        values.put(params.KEY_PHONE,contacts.getPhonenumber());
        db.insert(params.Table_Name,null,values);
        Log.d("fewf$$$$$$","%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%55");
        db.close();
    }


    public List<Contacts> getContacts(){
        SQLiteDatabase db =  this.getReadableDatabase();
String select = "SELECT * FROM "+params.Table_Name;
        List<Contacts> contactsList = new ArrayList<>();
        Cursor dd = db.rawQuery(select, null);
        if(dd.moveToFirst()){
            do{
                Contacts contacts = new Contacts(dd.getInt(0),dd.getString(1),dd.getString(2));
                contactsList.add(contacts);
            }while (dd.moveToNext());
        }



        return contactsList ;


    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create = "CREATE TABLE "+params.Table_Name +" ( "+params.KEY_ID + " INTEGER PRIMARY KEY, " +params.KEY_Name + " TEXT, " +params.KEY_PHONE + " TEXT" + ")";
        Log.d("pavana ","query create running ");
        sqLiteDatabase.execSQL(create);
    }

    public MyDbHandler(Context context) {
        super(context, params.DB_Name, null,params.DB_Version);
    }

    public void update(Contacts contacts) {


SQLiteDatabase sqlite= this.getWritableDatabase();
ContentValues values = new ContentValues();

        values.put(params.KEY_Name,contacts.getName());
        values.put(params.KEY_PHONE,contacts.getPhonenumber());


        sqlite.update(params.Table_Name,values,params.KEY_ID + " = ?",new String[]{String.valueOf(contacts.getId())});





    }

    public void getbyId(int i) {
        System.out.println("getbyId getbyIdgetbyIdgetbyIdgetbyIdgetbyIdgetbyId");


        SQLiteDatabase sql = this.getReadableDatabase();
        String  sqlquery = "SELECT * FROM "+params.Table_Name +" WHERE "+params.KEY_ID +" = ?";

        Cursor getContacts = sql.rawQuery(sqlquery, new String[]{String.valueOf(i)});
        List<Contacts> contactsList = new ArrayList<>() ;
        if(getContacts.moveToFirst()){
            do{
                contactsList.add(new Contacts(getContacts.getInt(0),getContacts.getString(1),getContacts.getString(2)));
            }while (getContacts.moveToNext());
        }
        System.out.println(contactsList);
    }

    public void getbyname(String pavan) {
        System.out.println("getbynamegetbynamegetbynamegetbynamegetbynamegetbynamegetbyname");

        SQLiteDatabase sql = this.getReadableDatabase();
        String  sqlquery = "SELECT * FROM "+params.Table_Name +" WHERE "+ params.KEY_Name +" = ?";

        Cursor getContacts = sql.rawQuery(sqlquery, new String[]{String.valueOf(pavan)});
        List<Contacts> contactsList = new ArrayList<>() ;
        if(getContacts.moveToFirst()){
            do{
                contactsList.add(new Contacts(getContacts.getInt(0),getContacts.getString(1),getContacts.getString(2)));
            }while (getContacts.moveToNext());
        }
        System.out.println(contactsList);

    }

    public void getbyphonemmber(String s) {
        System.out.println("getbyphonemmbergetbyphonemmbergetbyphonemmbergetbyphonemmbergetbyphonemmbergetbyphonemmber");
        SQLiteDatabase sql = this.getReadableDatabase();
        String  sqlquery = "SELECT * FROM "+params.Table_Name +" WHERE "+params.KEY_PHONE +" = ?";

        Cursor getContacts = sql.rawQuery(sqlquery, new String[]{String.valueOf(s)});
        List<Contacts> contactsList = new ArrayList<>() ;
        if(getContacts.moveToFirst()){
            do{
                contactsList.add(new Contacts(getContacts.getInt(0),getContacts.getString(1),getContacts.getString(2)));
            }while (getContacts.moveToNext());
        }
        System.out.println(contactsList);

    }

    public void deleteById(int i) {

        SQLiteDatabase sql = this.getWritableDatabase();

sql.delete(params.Table_Name,params.KEY_ID+"= ?",new String[]{String.valueOf(i)});


    }

    public void getCount() {

        String query  = "SELECT * FROM "+params.Table_Name;
        SQLiteDatabase db =getReadableDatabase();
        System.out.println(db.rawQuery(query,null).getCount());


    }
}
