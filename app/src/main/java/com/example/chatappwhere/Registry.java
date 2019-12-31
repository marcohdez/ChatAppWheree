package com.example.chatappwhere;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.RadioButton;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.HashMap;
import java.util.Map;


public class Registry extends AppCompatActivity implements View.OnClickListener {

    private TextInputLayout confirmarr, contraseñaa, nombree, puestoo, diaa, mess, añoo, correoo, telefonoo;
    private FloatingActionButton siguiente;
    private RadioButton hombree, mujerr;

    private String confirmar = "";
    private String contraseña = "";
    private String nombre = "";
    private String puesto = "";
    private String dia = "";
    private String mes = "";
    private String año = "";
    private String correo = "";
    private String telefono = "";

    FirebaseAuth auth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registry);
        //Cambie el menu
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.txt_menu);

        auth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        siguiente = (FloatingActionButton) findViewById(R.id.continuar);
        nombree = (TextInputLayout) findViewById(R.id.nombre);
        correoo = (TextInputLayout) findViewById(R.id.correo);
        contraseñaa = (TextInputLayout) findViewById(R.id.contraseña);
        confirmarr = (TextInputLayout) findViewById(R.id.buttonContinue);
        telefonoo = (TextInputLayout) findViewById(R.id.phone);
        puestoo = (TextInputLayout) findViewById(R.id.puesto);
        diaa = (TextInputLayout) findViewById(R.id.dia);
        mess = (TextInputLayout) findViewById(R.id.mes);
        añoo = (TextInputLayout) findViewById(R.id.año);

        hombree = (RadioButton) findViewById(R.id.hombre);
        mujerr = (RadioButton) findViewById(R.id.mujer);

        hombree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mujerr.setChecked(false);
            }
        });
        mujerr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hombree.setChecked(false);
            }
        });

    }

    private boolean validatenombre() {
        String nombreInput = nombree.getEditText().getText().toString().trim();

        if (nombreInput.isEmpty()) {
            nombree.setError("Ingrese su nombre");
            return false;

        } else {
            nombree.setError(null);
            return true;

        }
    }


    private boolean validatecorreo() {
        String correoInput = correoo.getEditText().getText().toString().trim();

        if (correoInput.isEmpty()) {
            correoo.setError("Ingrese su correo de la empresa");
            return false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(correoInput).matches()) {
            correoo.setError("Correo Invalido");
            return false;
        } else {
            correoo.setError(null);
            return true;
        }
    }

    private boolean validatecontraseña() {
        String contraseñaInput = contraseñaa.getEditText().getText().toString().trim();

        if (contraseñaInput.isEmpty()) {
            contraseñaa.setError("Ingrese su contraseña");
            return false;
        } else if (contraseñaInput.length() < 8) {
            contraseñaa.setError("Minimo 8 carecteres");
            return false;
        } else {
            contraseñaa.setError(null);
            return true;
        }
    }

    private boolean validateconfirmar() {
        String confirmarInput = confirmarr.getEditText().getText().toString().trim();

        if (confirmarInput.isEmpty()) {
            confirmarr.setError("Ingrese su contraseña");
            return false;
        } else if (confirmarInput.length() < 8) {
            confirmarr.setError("Minimo 8 carecteres");
            return false;
        } else {
            confirmarr.setError(null);
            return true;
        }
    }

    private boolean validatetelefono() {
        String telefonoInput = telefonoo.getEditText().getText().toString().trim();

        if (telefonoInput.isEmpty()) {
            telefonoo.setError("Ingrese su telefono");
            return false;
        } else if (telefonoInput.length() < 10) {
            telefonoo.setError("Numero invalido");
            return false;
        } else {
            telefonoo.setError(null);
            return true;
        }
    }

    private boolean validatetedia() {
        String diaInput = diaa.getEditText().toString().trim();

        if (diaInput.isEmpty()) {
            diaa.setError("Ingrese el dia");
            return false;
        } else if (diaInput.length() < 1) {
            diaa.setError("Dia invalido");
            return false;
        } else {
            diaa.setError(null);
            return true;
        }

    }

    private boolean validatemes() {
        String mesInput = mess.getEditText().toString().trim();

        if (mesInput.isEmpty()) {
            mess.setError("Ingrese el mes");
            return false;
        } else if (mesInput.length() < 2) {
            mess.setError("Mes invalido");
            return false;

        } else {
            mess.setError(null);
            return true;
        }

    }

    private boolean validateteaño() {
        String añoInput = añoo.getEditText().toString().trim();

        if (añoInput.isEmpty()) {
            añoo.setError("Ingrese el año");
            return false;
        } else if (añoInput.length() < 4) {
            añoo.setError("Año invalido");
            return false;
        } else {
            añoo.setError(null);
            return true;
        }

    }

    private boolean validatepuesto() {
        String puestoInput = puestoo.getEditText().getText().toString().trim();

        if (puestoInput.isEmpty()) {
            puestoo.setError("Ingrese su puesto que ocupa en appwhere");
            return false;

        } else {
            puestoo.setError(null);
            return true;
        }

    }


    public void Confirmar(View v) {
        if (!validatenombre() | !validatecorreo() | !validateconfirmar() | !validatecontraseña() | !validatepuesto() | !validatetelefono() | !validatetedia() | !validatemes() | !validateteaño()) {
            return;
        }


        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nombre = nombree.getEditText().getText().toString().trim();
                correo = correoo.getEditText().getText().toString().trim();
                contraseña = contraseñaa.getEditText().getText().toString().trim();
                confirmar = confirmarr.getEditText().getText().toString().trim();
                telefono = telefonoo.getEditText().getText().toString().trim();
                puesto = puestoo.getEditText().getText().toString().trim();
                dia = diaa.getEditText().getText().toString().trim();
                mes = mess.getEditText().getText().toString().trim();
                año = añoo.getEditText().getText().toString().trim();


                if (!nombre.isEmpty() && !correo.isEmpty() && !contraseña.isEmpty() && !confirmar.isEmpty() && !telefono.isEmpty() && !puesto.isEmpty() && !dia.isEmpty() && !mes.isEmpty() && !año.isEmpty()) {

                }
                registerUser();
            }
        });
    }

    private void registerUser() {
        auth.createUserWithEmailAndPassword(correo, nombre).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Map<String, Object> map = new HashMap<>();
                    map.put("nombre", nombre);
                    map.put("correo", correo);
                    map.put("contraseña", contraseña);
                    map.put("confirmar", confirmar);
                    map.put("telefono", telefono);
                    map.put("puesto", puesto);
                    map.put("dia", dia);
                    map.put("mes", mes);
                    map.put("año", año);

                    String id = auth.getCurrentUser().getUid();
                    mDatabase.child("Users").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if (task2.isSuccessful()) {
                                startActivity(new Intent(Registry.this, Login.class));
                                finish();
                            }
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onClick(View v) {

    }
}

