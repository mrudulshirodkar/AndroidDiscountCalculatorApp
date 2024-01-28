package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity{
    EditText editTextItemPrice;
    TextView textViewSeekBar, textViewDiscountValue, textViewFinalPriceValue;
    SeekBar seekBar;
    RadioGroup radioGroup;
    double itemprice, selectedsale, discount, finalprice;

    final String TAG = "demo";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextItemPrice = findViewById(R.id.editTextItemPrice);
        textViewSeekBar = findViewById(R.id.textViewSeekBar);
        radioGroup = findViewById(R.id.radioGroup);
        textViewDiscountValue = findViewById(R.id.textViewDiscountValue);
        seekBar = findViewById(R.id.seekBar);
        textViewFinalPriceValue = findViewById(R.id.textViewFinalPriceValue);

        radioGroup.check(R.id.radioButton45);
        seekBar.setEnabled(false);





        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                int checkedid = radioGroup.getCheckedRadioButtonId();
                if(checkedid != R.id.radioButton8){
                    seekBar.setEnabled(false);
                }else seekBar.setEnabled(true);
            }
        });

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Log.d(TAG, "onProgressChanged: " + i);

                textViewSeekBar.setText(i + "%");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        findViewById(R.id.buttonReset).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextItemPrice.setText("");
                radioGroup.check(R.id.radioButton45);
                seekBar.setEnabled(false);
                seekBar.setProgress(25);
                textViewDiscountValue.setText("0.00");
                textViewFinalPriceValue.setText("0.00");
            }
        });

        findViewById(R.id.buttonCalculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String text = editTextItemPrice.getText().toString();
                if(editTextItemPrice.getText().toString().isEmpty()){
                    textViewDiscountValue.setText("0.00");
                    textViewFinalPriceValue.setText("0.00");
                    Toast.makeText(MainActivity.this, "Enter Item Price", Toast.LENGTH_SHORT).show();
                } else if (containsAlphabets(text)){
                    Toast.makeText(MainActivity.this, "Enter Number", Toast.LENGTH_SHORT).show();
                } else if (Double.parseDouble(editTextItemPrice.getText().toString())<=0) {
                    Toast.makeText(MainActivity.this, "Enter Positive Number", Toast.LENGTH_SHORT).show();
                } else {
                    int checkedid = radioGroup.getCheckedRadioButtonId();
                    if(checkedid == R.id.radioButton45){
                        selectedsale = 10;
                        //seekBar.setProgress(10);

                    }else if(checkedid == R.id.radioButton6){
                        selectedsale = 15;
                        //seekBar.setProgress(15);

                    }else if(checkedid == R.id.radioButton5){
                        selectedsale = 18;
                        //seekBar.setProgress(18);

                    }else if(checkedid == R.id.radioButton8){
                        selectedsale = seekBar.getProgress();
                    }
                    itemprice = Double.parseDouble(editTextItemPrice.getText().toString());
                    discount = itemprice*(selectedsale/100);
                    finalprice = itemprice - discount;
                    //textViewDiscountValue.setText(String.valueOf(discount));
                    textViewDiscountValue.setText(discount + "");
                    textViewFinalPriceValue.setText(finalprice + "");
                }

            }
        });

    }

    private static boolean containsAlphabets(String text) {
        Pattern pattern = Pattern.compile("[a-zA-Z]");
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}