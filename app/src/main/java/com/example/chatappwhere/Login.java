package com.example.chatappwhere;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity implements View.OnClickListener {

    TextInputLayout etUsuario, etContra;
    FloatingActionButton btnIngresar;

    private String correo = "";
    private String contraseña = "";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Cambie el menu
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_menu);


        mAuth = FirebaseAuth.getInstance();
        etUsuario = (TextInputLayout) findViewById(R.id.etUsuario);
        etContra = (TextInputLayout) findViewById(R.id.etContra);
        btnIngresar = (FloatingActionButton) findViewById(R.id.btnIngresar);

    }

    private boolean validateEmail() {
        String emailInput = etUsuario.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            etUsuario.setError("Correo de la empresa");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailInput).matches()) {
            etUsuario.setError("Ingrese el corrreo de la empresa");
            return false;
        } else {
            etUsuario.setError(null);
            return true;

        }
    }


    private boolean validateUsername() {
        String usernameInput = etContra.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            etContra.setError("Ingrese su contraseña");
            return false;
        } else if (usernameInput.length() < 8) {
            etContra.setError("Minimo 8 carecteres");
            return false;
        } else {
            etContra.setError(null);
            return true;
        }
    }

    public void Confirmar(View v) {
        if (!validateEmail() | !validateUsername()) {
            return;
        }

        btnIngresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                correo = etUsuario.getEditText().getText().toString().trim();
                contraseña = etContra.getEditText().getText().toString().trim();

                if (!correo.isEmpty() && !contraseña.isEmpty()) {
                    loginUser();
                }
            }
        });
    }

    private void loginUser() {
        mAuth.signInWithEmailAndPassword(correo, contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(Login.this, Mainmenu.class));
                    finish();

                }
                else{
                    Toast.makeText(Login.this,"La direccion de correo electronico o la contraseña que has introducido no son correctas",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}
