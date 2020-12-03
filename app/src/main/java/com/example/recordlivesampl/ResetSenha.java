package com.example.recordlivesampl;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetSenha extends AppCompatActivity {
    private EditText ediEmail; // private declaração de variavel // para saber o que e variavel veja o intem  C Na activity Login
    private Button btnResetSenha;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_senha);
        inicializarComponentes(); // Linha de codigo para criar um Private Void veja em activity.java no Item A1
        eventoClick();
    }

    private void eventoClick() {
        // btnResetSenha, Aqui dizemos o que e!
        //setOnClickListener, aqui dizemos o que faz

        btnResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = ediEmail.getText().toString().trim();
                resetSenha(email);
            }
        });
    }

    private void resetSenha(String email) {
        auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(ResetSenha.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()){
                            alert("Um E-mail foi Enviado para alterar sua senha");
                            finish();

                        }else{
                            alert("E-Mail não Registrado");
                        }

                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(ResetSenha.this,s, Toast.LENGTH_SHORT).show();

    }


    private void inicializarComponentes() {
        ediEmail = (EditText) findViewById(R.id.editResetEmail);
        btnResetSenha = (Button) findViewById(R.id.btnResetSenha);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();

    }
}
