package com.example.john.databasetest;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

    public class DataBase extends SQLiteOpenHelper{

        public static final String DatabaseName="Yat.db";
        public static final String TableName="Users_Table";
        public static final String Col1="ID";
        public static final String Col2="Name";


        public DataBase(Context context) {
            super(context, DatabaseName, null, 1);
          //      SQLiteDatabase db=getWritableDatabase();
        }

        @Override
    public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table " + TableName +"(ID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT )" );


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TableName );
        onCreate(db);

    }
    public boolean InsertData(String name){
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(Col2,name);
        long result = db.insert(TableName,null,contentValues);
        if(result==-1){
            return false;
        }else{
            return true;
        }


    }
    public Cursor ViewAllData(){
        SQLiteDatabase db = getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TableName,null);
        return res;

    }
}
