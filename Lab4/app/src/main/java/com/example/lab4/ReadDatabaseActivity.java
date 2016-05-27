package com.example.lab4;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import manager.DatabaseManager;

public class ReadDatabaseActivity extends AppCompatActivity {
    private Button btnLoad;
    private TextView lblInfo;
    private DatabaseManager databaseManager;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_database);

        databaseManager = new DatabaseManager(this);
        database = databaseManager.getWritableDatabase();

        btnLoad     = (Button) findViewById(R.id.btnLoad);
        lblInfo     = (TextView) findViewById(R.id.textView);

        btnLoad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!database.isOpen()) {
                    database = databaseManager.getWritableDatabase();
                }

                Cursor cursor = database.query(DatabaseManager.TABLE_NAME, DatabaseManager.columnProjection, null, null, null, null, null);

                String result = "";
                if (null != cursor && cursor.getCount() > 0) {
                    if (cursor.moveToFirst()) {
                        if (!cursor.isAfterLast()) {
                            do {
                                result += "Name: " + cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_NAME)) + " \n";
                                result += "Operation Memory: " + cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_OPERATION_MEMORY)) + " \n";
                                result += "System Memory: " + cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_SYSTEM_MEMORY)) + " \n";
                                result += "--------- \n";
                            } while (cursor.moveToNext());
                        }
                    }
                } else {
                    result = "Empty";
                }

                lblInfo.setText(result);
                database.close();


            }
        });
    }
}
