package io.dovid.databaselocking;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Author: Umberto D'Ovidio
 * Date: 30/09/17
 * Email: umberto.dovidio@gmail.com
 * Website: http://dovid.io
 * Tutorial link : http://dovid.io/2017/09/30/gestire-sqlite-android.html
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = (Button) findViewById(R.id.singledbclose);
        Button btn2 = (Button) findViewById(R.id.multipledbclose);
        Button btn3 = (Button) findViewById(R.id.singledbopen);
        Button btn4 = (Button) findViewById(R.id.multipledbopen);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllRecords();

                ArrayList<Thread> threads = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    threads.add(new Thread(new InsertRandomThingsInDbSingleDBH(true, false, ( i+1) * 1000)));
                }

                int i = 1;
                for (Thread thred : threads) {
                    Log.d("RUNNING", "thread n: " + i++);
                    thred.start();
                }
                printAllTodo(true, false);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllRecords();

                ArrayList<Thread> threads = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    threads.add(new Thread(new InsertRandomThingsInDbSingleDBH(true, true, ( i+1) * 1000)));
                }

                int i = 1;
                for (Thread thred : threads) {
                    Log.d("RUNNING", "thread n: " + i++);
                    thred.start();
                }
                printAllTodo(true, true);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllRecords();

                ArrayList<Thread> threads = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    threads.add(new Thread(new InsertRandomThingsInDbSingleDBH(false, false, ( i+1) * 1000)));
                }

                int i = 1;
                for (Thread thred : threads) {
                    Log.d("RUNNING", "thread n: " + i++);
                    thred.start();
                }
                printAllTodo(false, false);
            }
        });

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteAllRecords();

                ArrayList<Thread> threads = new ArrayList<>();

                for (int i = 0; i < 10; i++) {
                    threads.add(new Thread(new InsertRandomThingsInDbSingleDBH(false, true, ( i+1) * 1000)));
                }

                int i = 1;
                for (Thread thred : threads) {
                    Log.d("RUNNING", "thread n: " + i++);
                    thred.start();
                }
                printAllTodo(false, true);
            }
        });
    }

    private void deleteAllRecords() {
        DatabaseHelper.getInstance(this).getWritableDatabase().delete("TODO", null, null);
    }


    private class InsertRandomThingsInDbSingleDBH implements Runnable {

        private int threadNumber;
        private boolean multipleDBH;
        private boolean closeDB;

        InsertRandomThingsInDbSingleDBH(boolean closeDB, boolean multipleDBH, int i) {
            this.closeDB = closeDB;
            this.multipleDBH = multipleDBH;
            this.threadNumber = i;
        }

        @Override
        public void run() {
            DatabaseHelper db;
            SQLiteDatabase wdb = null;

            try {
                if (multipleDBH) {
                    db = new DatabaseHelper(MainActivity.this);
                } else {
                    db = DatabaseHelper.getInstance(MainActivity.this);
                }

                wdb = db.getWritableDatabase();
                wdb.beginTransaction();

                for (int i = 0; i < 10; i++) {
                    Log.d("INSERTING", "item number: " + (threadNumber + i) );
                    ContentValues cv = new ContentValues();
                    cv.put("ID", threadNumber + i);
                    cv.put("NAME", "item n " + (threadNumber + i));
                    wdb.insert("TODO", null, cv);
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                wdb.setTransactionSuccessful();
                wdb.endTransaction();
            } catch (SQLiteException e) {
                throw new RuntimeException(e);
//                new AlertDialog.Builder(MainActivity.this).setMessage(e.toString()).create().show();
            } finally {
                if (closeDB && wdb != null && wdb.isOpen()) {
                    wdb.close();
                }
            }
        }
    }

    private void printAllTodo(boolean closeDB, boolean multipleDBH) {

        Cursor c = null;
        SQLiteDatabase wdb = null;

        try {
            DatabaseHelper db;
            if (multipleDBH) {
                db = new DatabaseHelper(this);
            } else {
                db = DatabaseHelper.getInstance(this);
            }

            wdb = db.getWritableDatabase();
            c = wdb.rawQuery("SELECT * FROM TODO", null);

            while (c.moveToNext()) {
                Log.d("READING", "ID number: " + c.getInt(c.getColumnIndexOrThrow("ID")));
            }
        } catch (SQLiteException e) {
            new AlertDialog.Builder(MainActivity.this).setMessage(e.toString()).create().show();

        } finally {
            if (closeDB && wdb != null && wdb.isOpen()) {
                wdb.close();
            }
            if (c != null) {
                c.close();
            }
        }
    }
}