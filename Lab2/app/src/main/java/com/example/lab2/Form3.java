package com.example.lab2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Form3 extends AppCompatActivity {

    public final static String EXTRA_MESSAGE = "com.example.lab2.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form3);
        Intent intent = getIntent();
        String message = intent.getStringExtra(EXTRA_MESSAGE);

        TextView txView = (TextView)findViewById(R.id.editText3);
        txView.setText(message);
    }

    public void sendMessage(View view) {
        Intent intent = new Intent();
        intent.putExtra("IS_FINISH_FORM_2", true);
        setResult(0, intent);
        this.finish();
    }
}
