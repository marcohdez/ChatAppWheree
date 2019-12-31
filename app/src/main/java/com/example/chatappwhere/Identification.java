package com.example.chatappwhere;


import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.zxing.Result;
import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Identification extends AppCompatActivity {
    EditText codigo1, codigo2;
    FloatingActionButton confirmar;
    ImageView scanner;
    private String USER = "";
    private String PASSWORD = "";
    private ZXingScannerView vistaescaner, vistaescaner2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);

        //modificiacion de menu
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_menu);


        codigo1 = (EditText) findViewById(R.id.codigo1);
        codigo2 = (EditText) findViewById(R.id.codigo2);
        scanner = (ImageView) findViewById(R.id.scanner);
        confirmar = (FloatingActionButton) findViewById(R.id.buttonContinue);

    }

    private boolean validateEmail() {
        String emailInput = codigo1.getText().toString().trim();

        if (emailInput.isEmpty()) {
            codigo1.setError("Ingrese el primer codigo");
            return false;
        } else if (emailInput.length() < 8) {
            codigo1.setError("Codigo invalido");
            return false;

        } else {
            codigo1.setError(null);
            return true;

        }
    }


    private boolean validateUsername() {
        String usernameInput = codigo2.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            codigo2.setError("Ingrese el segundo codigo");
            return false;
        } else if (usernameInput.length() < 8) {
            codigo2.setError("Codigo invalido");
            return false;
        } else {
            codigo2.setError(null);
            return true;
        }

    }

    public void continuar(View v) {
        if (!validateEmail() | !validateUsername()) {
            return;
        }

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cod1 = codigo1.getText().toString();
                String cod2 = codigo2.getText().toString();
                if (cod1.equals("appwhere") && cod2.equals("appwhere")) {
                    Intent intent = new Intent(getApplicationContext(), Menu.class);
                    startActivity(intent);

                }
else{
                    Toast.makeText(Identification.this,"El codigo de verificacion es incorrecto",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void Escanear(View view) {

        vistaescaner = new ZXingScannerView(this);
        vistaescaner2 = new ZXingScannerView(this);

        vistaescaner.setResultHandler(new zxingscanner());
        vistaescaner2.setResultHandler(new zxingscanner());

        setContentView(vistaescaner);
        setContentView(vistaescaner2);

        vistaescaner.startCamera();
        vistaescaner2.startCamera();

    }


    class zxingscanner implements ZXingScannerView.ResultHandler {
        @Override
        public void handleResult(Result result) {
            String dato = result.getText();
            setContentView(R.layout.activity_identification);

            vistaescaner.stopCamera();
            vistaescaner2.stopCamera();

            codigo1 = (EditText) findViewById(R.id.codigo1);
            codigo2 = (EditText) findViewById(R.id.codigo2);

            codigo1.setText(dato);
            codigo2.setText(dato);
        }
    }

}