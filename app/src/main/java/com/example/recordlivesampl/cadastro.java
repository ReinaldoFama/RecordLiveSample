package com.example.recordlivesampl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class cadastro extends AppCompatActivity {
    private EditText editEmail, editSenha;
    private Button btnRegistrar,btnVoltar;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializarComponentes();
        eventoClicks();

    }

    private void eventoClicks() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });
        // Botão de Registrar
        ////-----------------------------------------------------------------
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editEmail.getText().toString().trim();
                String Senha = editSenha.getText().toString().trim();
                criarUser(email, Senha);



            }
        });
    }
    private void criarUser(String email, String Senha){
        auth.createUserWithEmailAndPassword(email,Senha)
                 .addOnCompleteListener(cadastro.this, new OnCompleteListener<AuthResult>() {
                     @Override
                     public void onComplete(@NonNull Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             alert("Usuario Cadastrado com Sucesso");
                             Intent i = new Intent(cadastro.this,Perfil.class);
                             startActivity(i);
                             finish();

                         }else{
                             alert("Erro de Cadastro");

                         }

                     }
                 });


    }
    private void alert(String msg){
        Toast.makeText(cadastro.this,msg, Toast.LENGTH_SHORT).show();
    }

    private void inicializarComponentes() {
        editEmail = (EditText) findViewById(R.id.editCadastroEmail);
        editSenha = (EditText)findViewById(R.id.editCadastroSenha);
        btnRegistrar = (Button) findViewById(R.id.btnCadastroRegistrar);
        btnVoltar = (Button)findViewById(R.id.btnCadastroVoltar);
    }
    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }
}
