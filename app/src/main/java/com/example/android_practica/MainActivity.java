package com.example.android_practica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btn_guardar, btn_cancelar;
    boolean[] inputTextValidez = {false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_guardar = this.findViewById(R.id.btn_guardar);
        btn_cancelar = this.findViewById(R.id.btn_cancelar);

        EditText[] inputs = {
                findViewById(R.id.nombre),
                findViewById(R.id.apellidos),
                findViewById(R.id.email)
        };

        btn_guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!validate()) {
                    Toast.makeText(getApplicationContext(), "Debe introducir todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }

                finish();
            }
        });

        btn_cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        for(int i = 0; i < inputs.length; i++) {
            int index = i;
            EditText input = inputs[index];

            input.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(!charSequence.equals("")) inputTextValidez[index] = true;
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });
        }
    }

    public void onRadioButtonClick(View view) {
        switch (view.getId()) {
            case R.id.radioButton_hombre:
            case R.id.radioButton_mujer:
                inputTextValidez[3] = true;
                break;

            default:
                inputTextValidez[3] = false;
        }
    }

    public void onCheckBoxClick(View view) {
        boolean isChecked = ((CheckBox) view).isChecked();
        switch(view.getId()) {
            case R.id.checkbox_amigos:
            case R.id.checkbox_familiares:
            case R.id.checkbox_trabajo:
                if(isChecked) inputTextValidez[4] = true;
                else inputTextValidez[4] = false;
                break;

            default:
                inputTextValidez[4] = false;
        }
    }

    public boolean validate() {
        for(boolean input: inputTextValidez) {
            System.out.println(input);
            if(!input) {
                return false;
            }
        }

        return true;
    }
}