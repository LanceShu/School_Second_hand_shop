package com.our_company.school_second_hand_shop.DataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Lance on 2017/5/11.
 */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    public static final String CREATE_CARDB = "create table Car("
            +"id integer primary key autoincrement,"
            +"perNum text,"
            +"carYear text,"
            +"carPrice text,"
            +"carNum text)";

    public static final String CREATE_PERDB="create table Per("
            +"id integer primary key autoincrement,"
            +"name text,"
            +"pass text,"
            +"num text,"
            +"age text,"
            +"sex text,"
            +"birth text,"
            +"mail text)";

    public static final String CREATE_PROMISSDB = "create table Promiss("
            +"id integer primary key autoincrement,"
            +"number text,"
            +"imageId integer,"
            +"carYear text,"
            +"carPrice text,"
            +"carNum text,"
            +"carPromissPeople text,"
            +"carPromissShoper text,"
            +"carPromissPrice text,"
            +"carPromissYear text,"
            +"carPromissMoney text,"
            +"promissPercent text)";

    private Context context;

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_CARDB);
        db.execSQL(CREATE_PERDB);
        db.execSQL(CREATE_PROMISSDB);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Car");
        db.execSQL("drop table if exists Per");
        db.execSQL("drop table if exists Promiss");
        onCreate(db);
    }
}
