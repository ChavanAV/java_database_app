package com.example.newdb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB_Helper extends SQLiteOpenHelper {
    public DB_Helper(Context context) {

        super(context,"empdata.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create table emp(ID integer,name Text ,post Text, salary integer);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop table if exists emp");
    }

    public Boolean _insertdata(String ID, String name , String post , String salary){
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("ID",ID);
        cv.put("name",name);
        cv.put("post",post);
        cv.put("salary",salary);
        long r=DB.insert("emp",null,cv);
        if (r==-1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatedata( String ID,String name,String post ,String salary) {
        SQLiteDatabase DB=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("name",name);
        cv.put("post",post);
        cv.put("salary",salary);
        Cursor crsr=DB.rawQuery("select * from emp where ID=?",new String[]{ID});
        if (crsr.getCount()>0){
            long r=DB.update("emp",cv,"ID=?",new String[]{ID});
            if (r==-1){
                return false;
            }else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Boolean deletedata(String ID){
        SQLiteDatabase DB= this.getWritableDatabase();
        Cursor crsr=DB.rawQuery("select * from emp where ID=? ",new String[]{ID});
        if (crsr.getCount()>0){
            long r=DB.delete("emp","ID=?",new String[]{ID});
            if (r==-1){
                return false;
            }else {
                return true;
            }
        }
        else {
            return false;
        }
    }

    public Cursor getdata(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor crsr=DB.rawQuery("select * from emp",null);
        return crsr;
    }
}
