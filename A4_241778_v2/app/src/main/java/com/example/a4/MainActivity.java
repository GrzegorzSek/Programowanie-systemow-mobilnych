package com.example.a4;

import androidx.activity.result.contract.ActivityResultContract;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    EditText szerokosc;
    EditText dlugosc;
    EditText nr_tel;
    EditText tresc_sms;
    Button btn_get_result;

    ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if(result.getResultCode() == 78){
                        Intent intent = result.getData();

                        if(intent != null){
                            String data = intent.getStringExtra("result");
                            System.out.println(data);
                            tresc_sms.setText(data);
                        }
                    }
                }
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        szerokosc = (EditText) findViewById(R.id.editTextTextPersonName3);
        dlugosc = (EditText) findViewById(R.id.editTextTextPersonName4);
        nr_tel = (EditText) findViewById(R.id.nr_tel);
        tresc_sms = (EditText) findViewById(R.id.tresc_sms);
        btn_get_result = (Button) findViewById(R.id.button);

        btn_get_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                Bundle myDataBundle = new Bundle();

                myDataBundle.putString("nr_indeksu", nr_tel.getText().toString());
                intent.putExtras(myDataBundle);
                activityLauncher.launch(intent);
            }
        });
    }

    public void mapOnClick(View view) {
        String geoCode = "geo:" + szerokosc.getText().toString() + ", " + dlugosc.getText().toString();
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoCode));
        startActivity(intent);
    }

    public void composeMmsMessage(View view) {
        Uri uri = Uri.parse("smsto: " + nr_tel.getText().toString());
        Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
        intent.putExtra("sms_body", tresc_sms.getText().toString());
        startActivity(intent);
    }

    public void openWebPage(View view) {
        String url = "https:" + nr_tel.getText().toString();
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        startActivity(intent);
    }
}