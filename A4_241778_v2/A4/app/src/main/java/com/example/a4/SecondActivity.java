package com.example.a4;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class SecondActivity extends AppCompatActivity {
    EditText nr_indeksu_text;
    Button odeslij_dane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        nr_indeksu_text = (EditText) findViewById(R.id.editTextTextPersonName);
        odeslij_dane = (Button) findViewById(R.id.button5);

        Intent myLocalIntent = getIntent();
        Bundle myBundle = myLocalIntent.getExtras();

        String nr_indeksu = myBundle.getString("nr_indeksu");
        nr_indeksu_text.setText(nr_indeksu);


        String msg;

        if (nr_indeksu.equals("241778")) {
            msg = "Grzegorz Sęk, ocena: 5";
        } else {
            msg = "uknown";
        }

        odeslij_dane.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.putExtra("result", msg);
                setResult(78, intent);

                SecondActivity.super.onBackPressed();
            }
        });

    }
}
