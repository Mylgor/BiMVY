package com.example.lab8;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DataMark extends AppCompatActivity {

    private TextView txtTitle;
    private Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mark);

        txtTitle = (TextView)findViewById(R.id.txtTitle);
        btnBack = (Button)findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        txtTitle.setText(message);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeForm();
            }
        });
    }

    private void closeForm()
    {
        this.finish();
    }
}
