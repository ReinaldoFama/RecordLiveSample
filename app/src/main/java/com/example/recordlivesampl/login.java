package com.example.recordlivesampl;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    // C
    // as variaveis
    // Cada variavel funciona como um granpeador onde onde você diz o que e logo em seguida diz o sera? Explicação confusa?
    // Basta penssar que ao inves de usar dinheiro vivo você vai pagar em creditos, os creditos são uma variavel enquanto dinheiro e um objeto.
    // sendo assim 4 $ Reais (Objeto Dinheiro ) =  4 creditos (variavel) os creditos recebem 4 reais objetos sendo assim creditos e dinheiro!
    private EditText edtEmail, editSenha; // Aqui dizemos que a variavel edtemail e senha , são inguais a um gadget editor de texto.
    private Button btnLogar, btnNovo; // O mesmo vale para buttão estamos dizendo aqui que a variavel btn e ingual a um Gadget botão.
    private TextView txtResetSenha;
    private FirebaseAuth auth; // Variavel firebase "aut" essa variavel esta recebendo a ação do oBjeto FirebaseAut C1

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        // essa e a areda de trabalho do usuario final aqui e onde introduzimos tudo que o usuario final ira fazer durante seu acesso.

        inicializaComponentes(); // Essa Linha de Codigo e apenas um informa  onde o caminho dos componentes. A1
        eventoClicks(); // eventosClicl assim como Inicilizar componentes e o caminho onde o codigo de ação sera introduzida. B1
    }

    private void eventoClicks() { //B1 aqui temos a introdução dos eventos OnCLik e nessa area que introduziremos os codigos de ações que teremos durante para cada objetos colocado na layout
        //b1 Verifique que btn a variavel que recebe o valor do objeto buttão introduzido na layout login
        btnNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { // b1-1 .setOnClickListner essa e a linha de codigo que vai gerar a ação do botão
                Intent i = new Intent(getApplicationContext(),cadastro.class); // B1- 2 Intent "i"  o i e uma variavel que esta recebendo o objeto Intent = e estamos informando que
                // Intent (no caso "I") vai abrir um a activity Cadastro.class estamos chamando a ativity cadastro com esse codigo.
                // quando o usuario final clicar nesse botão vai abrir a layout cadastro para ele.
                startActivity(i); // B1-3 Dispensa comentarios!


            }
        });
        /////////// Botão Logar \\\\\\\\\

        btnLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  // Veja a explicação da B1
                String email = edtEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();
                Login(email,senha);

            }
        });
        txtResetSenha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //Veja a Explicação da B-1
                Intent i = new Intent(login.this,ResetSenha.class);
                startActivity(i);


            }
        });




    }

    private void Login(String email, String senha) { //C1
        auth.signInWithEmailAndPassword(email, senha)// Para um bom intendedor meia palavra basta, aqui esta dizendo claramente que a variavael auth. tem a função
                // de email e password.
                .addOnCompleteListener(login.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) { // aqui estamos dizendo para chamar a atividade login If(se?) else(se não!)
                   // Os humanos so possui duas ações em suas vida inteiras Sim (Certasa ) e Se não (mediação)? confuso? na verdade não! e bastante sinples se para para penssar
                   // veja um exemplo, em um casa a uma porta e uma janela a janela esta a berta e a porta esta feichada, você tem que chegar a escola em 1 hora
                        // se(if) você achar as chaves sai pela porta e chegue no horario se não(else) sai pela janela e chegue no horario.
                        //IF representa ação comcreta e else Representa ação intermediaria! mas ambas são resposta positivas a ação tomada.

                        if (task.isSuccessful()){ // aqui estamos dizendo o seguinte se task(tarefa em ingles) for um sucesso! a ação a ser tomada e
                            Intent i = new Intent(login.this,dashboard.class); // abrir a  layout dashibor.
                            startActivity(i);
                        }else{ // se task(tarefa em ingles) não foi um sucesso alerte ao usuario final
                            alert("E-mail ou Senha Errados"); // que email e senha estão errado.

                        }

                    }
                });
    }

    private void alert(String s) {
        Toast.makeText(login.this,s, Toast.LENGTH_SHORT).show();
    } // C1- essa linha de codigo esta dizendo o seguinte que e para mostrar na tela uma menssagem longa  na tela login.
    //A1-4 // // private void a interpretação mais leiga para private void e Caixa de codigo que não e visivel ao publico.
    // aqui você apena introduz o codigo que agirar por tras das ações do usuario.
    private void inicializaComponentes() { // A1 Codigo privado que liga os elementos da res layout Login Ao Codigo login Java
      //A1-1  // edtEmail Variavel que recebe função email
        //A1-2  // (EditText) Dentro ca caixa estamos informando o que e que a variavel e nesse caso e um gadget  editor de texto
        //A1-3 // (Button) com o bottão e o mesmo a você cria uma variavel e diz o que ela e e logo emseguida onde ela esta.
        edtEmail  = (EditText) findViewById(R.id.editLoginEmail); // caminho da caixa onde se introduz o email
        editSenha = (EditText) findViewById(R.id.editLoginSenha); // Caminho da caixa onde se introduz a senha
        btnLogar = (Button) findViewById(R.id.btLoginLogar); // caminho do botão de logar
        btnNovo = (Button) findViewById(R.id.editLoginNovo); // caminho do botão de nova senha
        txtResetSenha = (TextView) findViewById(R.id.txtResetSenha); // caminho do botão de alterar senha.
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
    }

    //O tempo de vida visível de uma atividade ocorre entre uma chamada e onStart()até uma chamada correspondente onStop(). Durante esse período, o usuário pode ver a atividade na tela, apesar de não estar em primeiro plano e interagir com o usuário. Entre esses dois métodos, você pode manter os recursos necessários para mostrar a atividade ao usuário. Por exemplo, você pode registrar um BroadcastReceiverem onStart () para monitorar alterações que afetam sua interface do usuário e cancelar o registro em onStop () quando o usuário não vê mais o que está sendo exibido. Os métodos onStart () e onStop () podem ser chamados várias vezes, à medida que a atividade se torna visível e oculta ao usuário.

}
