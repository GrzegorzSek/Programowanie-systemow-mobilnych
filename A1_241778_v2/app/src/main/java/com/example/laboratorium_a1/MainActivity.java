package com.example.laboratorium_a1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;

    //my imports
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
    //importy skopiowane
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    //class variables
    private Context context;
    private final int duration = Toast.LENGTH_SHORT;
    //Screen colors + Exit button
    private Button btnExit;
    private EditText txtColorSelected;
    private LinearLayout Screen;
    private String PREFNAME = "myPrefFile1";
    private TextView txtSpyBox;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == 1000 || requestCode == 1) {
            if(grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Permission granted!", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(this, "Permission not granted!", Toast.LENGTH_SHORT).show();
                finish();
            }
    }

}

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Wiring GUI controls
        txtColorSelected = (EditText)findViewById(R.id.editText1);
        btnExit = (Button)findViewById(R.id.button1);
        Screen = (LinearLayout)findViewById(R.id.Screen1);
        txtSpyBox = (TextView)findViewById(R.id.textView1);

        //Set GUI Listeners and watchers
        btnExit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //observe text changes
        txtColorSelected.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //empty
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //empty
            }

            @Override
            public void afterTextChanged(Editable s) {
                //set background color
                String chosenColor = s.toString().toLowerCase(Locale.US);
                txtSpyBox.setText(chosenColor);
                setBackgroundColor(chosenColor, Screen);
            }
        });

        //show the current state's name
        context = getApplicationContext();
        Toast.makeText(context, "onCreate", duration).show();

        //receiveSMS
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.RECEIVE_SMS)
        != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.RECEIVE_SMS}, 1000);
        }
        //receivePhoneCall
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_PHONE_STATE}, 1000);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        updateMeUsingSavedStateData();

        Toast.makeText(context, "onStart", duration).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(context, "onResume", duration).show();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Toast.makeText(context, "onSaveInstanceState", duration).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String chosenColor = txtSpyBox.getText().toString();
        saveStateData(chosenColor);

        Toast.makeText(context, "onPause", duration).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(context, "onStop", duration).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(context, "onDestroy", duration).show();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Toast.makeText(context, "onRestart", duration).show();
    }
    //functions (changing color, exit button)
    private void setBackgroundColor(String chosenColor, LinearLayout Screen){
        if (chosenColor.contains("czerwony"))
            Screen.setBackgroundColor(0xffff0000);
        if (chosenColor.contains("zielony"))
            Screen.setBackgroundColor(0xff00ff00);
        if (chosenColor.contains("niebieski"))
            Screen.setBackgroundColor(0xff0000ff);
        if (chosenColor.contains("bialy"))
            Screen.setBackgroundColor(0xffffffff);
    }

    private void saveStateData(String chosenColor){
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);

        SharedPreferences.Editor myPrefEditor = myPrefContainer.edit();
        String key = "chosenBackgroundColor";
        String value = txtSpyBox.getText().toString();
        myPrefEditor.putString(key, value);
        myPrefEditor.apply();
    }

    private void updateMeUsingSavedStateData(){
        SharedPreferences myPrefContainer = getSharedPreferences(PREFNAME, Activity.MODE_PRIVATE);

        String key = "chosenBackgroundColor";
        String defaultValue = "white";

        if ((myPrefContainer != null) && myPrefContainer.contains(key)){
            String color = myPrefContainer.getString(key, defaultValue);
            setBackgroundColor(color, Screen);
        }
    }
}
