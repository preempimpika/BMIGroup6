package com.example.bmigroup6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Resources res = getResources();
        final String[] rankArray = res.getStringArray(R.array.ranktext);
        final EditText weight_input = (EditText) findViewById(R.id.weightinput);
        weight_input.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8,2)});
        final EditText height_input = (EditText) findViewById(R.id.heightinput);
        height_input.setFilters(new InputFilter[]{new DecimalDigitsInputFilter(8,2)});
        final TextView bmi_output = findViewById(R.id.bmioutput);
        final TextView rank_output = findViewById(R.id.rankoutput);

        final Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double heightmeter = 0.0;
                heightmeter = Float.parseFloat(height_input.getText().toString());
                heightmeter = heightmeter/100;
                heightmeter *= heightmeter;
                double kilogram = 0.0;
                kilogram = Float.parseFloat(weight_input.getText().toString());
                double bmicalculator = 0.0;
                bmicalculator = kilogram/heightmeter;
                bmi_output.setText((new DecimalFormat("#,###.##").format(bmicalculator)+""));
                Resources resources =getResources();
                if(bmicalculator<16){
                    rank_output.setText(rankArray[0]);
                }else if(bmicalculator<17){
                    rank_output.setText(rankArray[1]);
                }else if(bmicalculator<18.5){
                    rank_output.setText(rankArray[2]);
                }else if(bmicalculator<25){
                    rank_output.setText(rankArray[3]);
                }else if(bmicalculator<30){
                    rank_output.setText(rankArray[4]);
                }else if(bmicalculator<35){
                    rank_output.setText(rankArray[5]);
                    rank_output.setTextColor(getResources().getColor(R.color.black));
                    rank_output.setBackground(getResources().getDrawable(R.drawable.borderoutput));
                }else if(bmicalculator<40){
                    rank_output.setText(rankArray[6]);
                    rank_output.setTextColor(getResources().getColor(R.color.black));
                    rank_output.setBackground(getResources().getDrawable(R.drawable.borderoutput2));
                }else if(bmicalculator>40){
                    rank_output.setText(rankArray[7]);
                    rank_output.setTextColor(getResources().getColor(R.color.black));
                    rank_output.setBackground(getResources().getDrawable(R.drawable.borderoutput3));

                }
            }
        });
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if ((newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) ||
                (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT)) {
        }

        if ((newConfig.screenLayout & Configuration.SCREENLAYOUT_SIZE_MASK) == Configuration.SCREENLAYOUT_SIZE_LARGE) {

        }

        float fontScale = newConfig.fontScale;
    }
}


class DecimalDigitsInputFilter implements InputFilter {
    private Pattern mPattern;

    DecimalDigitsInputFilter(int digits, int digitsAfterZero) {
        mPattern = Pattern.compile("[0-9]{0," + (digits - 1) + "}+((\\.[0-9]{0," + (digitsAfterZero - 1) +
                "})?)||(\\.)?");
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        Matcher matcher = mPattern.matcher(dest);
        if (!matcher.matches())
            return "";
        return null;
    }
}