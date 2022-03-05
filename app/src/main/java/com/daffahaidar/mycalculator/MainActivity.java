package com.daffahaidar.mycalculator;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //Deklarasi tipe data
    EditText editValue1, editValue2;
    Button buttonCalc;
    TextView result;
    RadioGroup radioOperators;
    String OPERATOR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //memanggil method layout view
        setContentView(R.layout.activity_main);
        //memanggil method setupView
        setupView();
        // memanggil method ketika button di klik
        setupListener();

    }

    // method ambil value dari XML
    private void setupView() {
        // casting type data
        editValue1 = findViewById(R.id.edit_value1); //diambil dari id XML
        editValue2 = findViewById(R.id.edit_value2); //diambil dari id XML
        buttonCalc = findViewById(R.id.button_calc); //diambil dari id XML
        result = findViewById(R.id.text_result); //diambil dari id XML
        radioOperators = findViewById(R.id.radio_operators);
    }

    // method click tombol
    private void setupListener(){
        // apabila tombol hitung di click
        buttonCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            // method pengambilan value
            public void onClick(View view) {

                // validation
                if (validate()){
                    //deklarasi variable
                    Double value1 = Double.parseDouble(editValue1.getText().toString());
                    Double value2 = Double.parseDouble(editValue2.getText().toString());

                    //menampilkan hasil
                    result.setText(
                            value(value1, value2)
                    );
                }else{
                    showMessage("Masukan data dengan benar!");
                }
            }
        });

        // untuk pemilihan radio button
        radioOperators.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton radioButton = findViewById(group.getCheckedRadioButtonId());
                OPERATOR = radioButton.getText().toString();
                result.setText("Hasil Perhitungan");
            }
        });
    }

    // method validasi ketika user salah input / kosong
    private Boolean validate(){

        //jika user tidak mengisi nilai
        if (editValue1.getText().toString().equals("") || editValue1.getText() == null){
            return false;
        } else if (editValue2.getText().toString().equals("") || editValue2.getText() == null){
            return false;
        } else if (OPERATOR == null){
            return false;
        }

        //jika tidak terdapat masalah
        return true;
    }

    // method aritmatika
    private String value(Double value1, Double value2){

        Double value = 0.0;

        switch (OPERATOR){
            case "+" :
                value = value1 + value2;
                break;
            case "-" :
                value = value1 - value2;
                break;
            case "x" :
                value = value1 * value2;
                break;
            case ":" :
                value = value1 / value2;
                break;
            case "Modulus" :
                value = value1 % value2;
                break;
        }
        return String.valueOf(value);
    }

    // method untuk menampilkan popup
    private void showMessage(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}