package com.example.a3_v2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MainActivity extends AppCompatActivity {

    TextView workspaceTextView;
    TextView historyTextView;

    String workspace = "";
    String history = "";
    String tempResult = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initTextViews();
    }

    private void initTextViews()
    {
        workspaceTextView = (TextView)findViewById(R.id.textView3);
        historyTextView = (TextView)findViewById(R.id.textView4);
    }

    private void setWorkspace(String givenValue)
    {
        workspace = workspace + givenValue;
        workspaceTextView.setText(workspace);
    }

    public void resultOnClick(View view) {
        Double result = null;
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("rhino");

        try {
            result = (double)engine.eval(workspace);
        } catch (ScriptException e)
        {
            Toast.makeText(this, "Invalid Input", Toast.LENGTH_SHORT).show();

        }

        if(result != null)
            history = history + workspace;
            tempResult = String.valueOf(result.doubleValue());
            history = history + " = " + tempResult + "\n";
            historyTextView.setText(history);
            workspaceTextView.setText(tempResult);
            workspace = tempResult;

    }

    public void divideOnClick(View view) {
        setWorkspace("/");
    }

    public void multiplyOnClick(View view) {
        setWorkspace("*");
    }

    public void plusOnClick(View view) {
        setWorkspace("+");
    }

    public void minusOnClick(View view) {
        setWorkspace("-");
    }

    public void clearOnClick(View view) {
        workspaceTextView.setText("");
        workspace = "";
    }

    public void oneOnClick(View view) {
        setWorkspace("1");
    }

    public void twoOnClick(View view) {
        setWorkspace("2");
    }

    public void threeOnClick(View view) {
        setWorkspace("3");
    }

    public void fourOnClick(View view) {
        setWorkspace("4");
    }

    public void fiveOnClick(View view) {
        setWorkspace("5");
    }

    public void sixOnClick(View view) {
        setWorkspace("6");
    }

    public void sevenOnClick(View view) {
        setWorkspace("7");
    }

    public void eightOnClick(View view) {
        setWorkspace("8");
    }

    public void nineOnClick(View view) {
        setWorkspace("9");
    }

    public void zeroOnClick(View view) {
        setWorkspace("0");
    }
}