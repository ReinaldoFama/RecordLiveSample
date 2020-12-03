package com.example.recordlivesampl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {
    private TextView textView,textEmail, textID;
    private Button btnLogOut;
    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializarComponentes();
        eventoClicck();
    }

    private void eventoClicck() {
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Conexao.logOut();
                finish();

            }
        });

    }

    private void inicializarComponentes() {
        textEmail = (TextView) findViewById(R.id.textPerfilEmail);
        textID = (TextView) findViewById(R.id.textPerfilId);
        btnLogOut = (Button) findViewById(R.id.btnPerfilLogout);

    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();

    }

    private void verificaUser() {
        if (user == null){
            finish();
        }else{
            textEmail.setText("Email: "+user.getEmail());
            textID.setText("ID: "+user.getUid());
        }

    }
}
