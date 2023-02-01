package com.example.a2_v1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.RatingBar;
import android.widget.SeekBar;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    DecimalFormat decimal = new DecimalFormat("###,###,###,###.##");

    Button buttonCalculate;
    RadioGroup radioGroup;
        RadioButton radioButtonTip10;
        RadioButton radioButtonTip15;
        RadioButton radioButtonTip20;

    RatingBar ratingBar;

    SeekBar seekBar;

    EditText sumNumber;
    EditText foodCostValue;
    EditText tipValue;
    //dodać ratingbar i suwak

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sumNumber = (EditText) findViewById(R.id.sumNumber);

        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
            radioButtonTip10 = (RadioButton) findViewById(R.id.radioButtonTip10);
            radioButtonTip15 = (RadioButton) findViewById(R.id.radioButtonTip15);
            radioButtonTip20 = (RadioButton) findViewById(R.id.radioButtonTip20);
        //rating bar
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        //seekbar
        seekBar = (SeekBar) findViewById(R.id.seekBar);

        //summary
        foodCostValue = (EditText) findViewById(R.id.foodCostValue);
        foodCostValue.setInputType(EditorInfo.TYPE_NULL);

        tipValue = (EditText) findViewById(R.id.tipValue);
        tipValue.setInputType(EditorInfo.TYPE_NULL);

        //button
        buttonCalculate = (Button) findViewById(R.id.buttonCalculate);

        buttonCalculate.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    double getTipValue= 0;
                    //sumNumber
                    String sumNumberString = sumNumber.getText().toString();
                    double sumNumber = Double.parseDouble(sumNumberString);

                    //tipValue
                    int radioId = radioGroup.getCheckedRadioButtonId();

                    if(radioButtonTip10.getId() == radioId)
                        getTipValue = 0.10;
                    if(radioButtonTip15.getId() == radioId)
                        getTipValue = 0.15;
                    if(radioButtonTip20.getId() == radioId)
                        getTipValue = 0.20;

                    //serviceRating
                    float serviceRatingValueFloat = ratingBar.getRating();
                    double ratingValue = (double) serviceRatingValueFloat;

                    //FoodRating (seekBar)
                    double seekBarValue= seekBar.getProgress();

                    //summary
                    double finalTipValue = getTipValue * sumNumber;
                    double finalCostValue = sumNumber - ratingValue - (seekBarValue / 10);

                    if(finalCostValue < 0){
                        finalCostValue = sumNumber;
                    }

                    String finalTipValueString = String.valueOf(decimal.format(finalTipValue)) + " zl";
                    String finalCostValueString = String.valueOf(decimal.format(finalCostValue)) + " zl";

                    foodCostValue.setText(finalCostValueString);
                    tipValue.setText(finalTipValueString);

                } catch (NumberFormatException e){
                    //ignore errors
                }
            }
        });
    }
}