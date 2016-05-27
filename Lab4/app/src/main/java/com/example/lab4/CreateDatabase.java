package com.example.lab4;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import manager.DatabaseManager;

public class CreateDatabase extends AppCompatActivity {
    private Button btnSave;
    private DatabaseManager databaseManager;
    private SQLiteDatabase database;
    private EditText etName, etOperMem, etSysMem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_database);

        databaseManager = new DatabaseManager(this);
        btnSave     = (Button) findViewById(R.id.btnSave);
        etName      = (EditText) findViewById(R.id.editTextName);
        etOperMem   = (EditText) findViewById(R.id.editTextOperMem);
        etSysMem    = (EditText) findViewById(R.id.editTextSysMem);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = databaseManager.getWritableDatabase();

                ContentValues cv = new ContentValues();
                cv.put(DatabaseManager.COLUMN_NAME_NAME, etName.getText().toString());
                cv.put(DatabaseManager.COLUMN_NAME_OPERATION_MEMORY, Integer.parseInt(etOperMem.getText().toString()));
                cv.put(DatabaseManager.COLUMN_NAME_SYSTEM_MEMORY, Integer.parseInt(etSysMem.getText().toString()));

                long result = database.insert(DatabaseManager.TABLE_NAME, null, cv);
                database.close();
            }
        });
    }
}
