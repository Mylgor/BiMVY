package com.example.lab5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button btnJsonParse, btnXmlParse;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJsonParse = (Button)findViewById(R.id.btnJsonParse);
        btnXmlParse = (Button)findViewById(R.id.btnXmlParse);

        btnJsonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JsonParse.class);
                startActivity(intent);
            }
        });

        btnXmlParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, XmlParse.class);
                startActivity(intent);
            }
        });
    }
}
