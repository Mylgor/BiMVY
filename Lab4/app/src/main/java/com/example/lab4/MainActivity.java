package com.example.lab4;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.RequiresPermission;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import manager.DatabaseManager;

public class MainActivity extends AppCompatActivity {
    private DatabaseManager databaseManager;
    private SQLiteDatabase database;
    private Button btnCreateBd, btnReadBd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnCreateBd = (Button) findViewById(R.id.btnCreateBD);
        btnReadBd = (Button) findViewById(R.id.btnReadBD);

        if (IsEmptyBd()) {
            btnCreateBd.setVisibility(View.GONE);
        }

        btnCreateBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateDatabase.class);
                startActivity(intent);
            }
        });

        btnReadBd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReadDatabaseActivity.class);
                startActivity(intent);
            }
        });
    }

    private boolean IsEmptyBd() {
        databaseManager = new DatabaseManager(this);
        database = databaseManager.getWritableDatabase();
        if (!database.isOpen()) {
            database = databaseManager.getWritableDatabase();
        }

        Cursor cursor = database.query(DatabaseManager.TABLE_NAME, DatabaseManager.columnProjection, null, null, null, null, null);

        String result = "";
        if (null != cursor && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                if (!cursor.isAfterLast()) {
                    result += cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_NAME)) + " \n";
                    result += cursor.getString(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_OPERATION_MEMORY)) + " \n";
                    result += cursor.getInt(cursor.getColumnIndex(DatabaseManager.COLUMN_NAME_SYSTEM_MEMORY)) + " \n";
                }
            }
        }

        database.close();
        if (result != "")
            return false;
        return true;
    }
}
