package com.example.volley_bike.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import com.example.volley_bike.Model.Bike;
import java.util.ArrayList;

public class Database extends SQLiteOpenHelper {


    public Database(Context applicationContext) {
        super(applicationContext, "EmployeeDatabase.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String tableEmp = "create table emp(BikeModelID text,BrandID text,ModelName text)";
        db.execSQL(tableEmp);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertData(Long bikemodel, Long brandid, String modelname) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("BikeModelID", bikemodel);
        values.put("BrandID", brandid);
        values.put("ModelName", modelname);


        sqLiteDatabase.insert("emp", null, values);


    }


    public ArrayList fetchData() {
        ArrayList<Bike> stringArrayList = new ArrayList<>();
        String fetchdata = "select * from emp";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery(fetchdata, null);

        if (cursor.moveToFirst()) {
            do {
                Bike b = new Bike();

                b.setBikeModelID(cursor.getLong(cursor.getColumnIndex("BikeModelID")));
                b.setBrandID(cursor.getLong(cursor.getColumnIndex("BrandID")));
                b.setModelName(cursor.getString(cursor.getColumnIndex("ModelName")));

                stringArrayList.add(b);


            } while
            (cursor.moveToNext());

        }

        return stringArrayList;

    }

}

