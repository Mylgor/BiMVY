package com.example.lab6;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import parser.MyIntentService;
import parser.Parse;

public class MainActivity extends AppCompatActivity {

    public static String EXP_CODE = "expression";
    public static String INT_CODE = "intent";
    public static String SERV_CODE = "servis";

    private TextView inputField;
    private Button btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    private Button btnMultiplic, btnDevision, btnPlus, btnMunis, btnEqual, btnErase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputField = (TextView)findViewById(R.id.txtInputField);
        btnZero = (Button)findViewById(R.id.btnZero);
        btnOne = (Button)findViewById(R.id.btnOne);
        btnTwo = (Button)findViewById(R.id.btnTwo);
        btnThree = (Button)findViewById(R.id.btnThree);
        btnFour = (Button)findViewById(R.id.btnFour);
        btnFive = (Button)findViewById(R.id.btnFive);
        btnSix = (Button)findViewById(R.id.btnSix);
        btnSeven = (Button)findViewById(R.id.btnSeven);
        btnEight = (Button)findViewById(R.id.btnEight);
        btnNine = (Button)findViewById(R.id.btnNine);

        btnMultiplic = (Button)findViewById(R.id.btnMultiplic);
        btnDevision = (Button)findViewById(R.id.btnDevision);
        btnPlus = (Button)findViewById(R.id.btnPlus);
        btnMunis = (Button)findViewById(R.id.btnMinus);
        btnEqual = (Button)findViewById(R.id.btnEqually);
        btnErase = (Button)findViewById(R.id.btnErase);

        btnZero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "0");
            }
        });

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "1");
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "2");
            }
        });

        btnThree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "3");
            }
        });

        btnFour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "4");
            }
        });

        btnFive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "5");
            }
        });

        btnSix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "6");
            }
        });

        btnSeven.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "7");
            }
        });

        btnEight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "8");
            }
        });

        btnNine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "9");
            }
        });

        btnMunis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "-");
            }
        });

        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "+");
            }
        });

        btnMultiplic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "*");
            }
        });

        btnDevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputField.setText(inputField.getText() + "/");
            }
        });

        btnEqual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //проверка интернета
                if (!isOnline()) {
                    Toast.makeText(getApplicationContext(), "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
                    return;
                }

                String expres = inputField.getText().toString();
                if (expres != "") {
                    doService(expres);
                }
            }
        });

        btnErase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eraseField();
            }
        });

        if (!isOnline()) {
            Toast.makeText(getApplicationContext(), "Нет соединения с интернетом!", Toast.LENGTH_LONG).show();
            return;
        }
    }





    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1)
        {
            if (resultCode == 1)
            {
                if (null != inputField) {
                    String answer = data.getStringExtra(SERV_CODE);
                    inputField.setText(answer);
                }
            }
        }
    }

    private void doService(String expression) {

        Intent empty = new Intent();
        PendingIntent pIntent = createPendingResult(1, empty, 0);

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra(EXP_CODE, expression);
        intent.putExtra(INT_CODE, pIntent);

        startService(intent);
    }


    private void eraseField()
    {
            StringBuffer str = new StringBuffer(inputField.getText().toString());
            if (str.length() > 0) {
                str.delete(str.length() - 1, str.length());
                inputField.setText(str);
            }
    }

    public boolean isOnline() {

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        if (activeNetwork != null && activeNetwork.isConnected()) {

            return true;
        }

        return false;
    }
}
