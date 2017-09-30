package io.dovid.databaselocking;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Author: Umberto D'Ovidio
 * Date: 30/09/17
 * Email: umberto.dovidio@gmail.com
 * Website: http://dovid.io
 * Tutorial link : http://dovid.io/2017/09/30/gestire-sqlite-android.html
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static DatabaseHelper db;

    public DatabaseHelper(Context context) {
        super(context.getApplicationContext(), "db", null, 1);

    }

    public static synchronized DatabaseHelper getInstance(Context context) {
        if (db == null) {
            db = new DatabaseHelper(context);
        }
        return db;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE TODO (ID INTEGER PRIMARY KEY, NAME TEXT NOT NULL)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}