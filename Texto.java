package com.example.recordlivesampl;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Texto extends AppCompatActivity {
    Button btnsalvar;
    EditText ednome, edar, edloc, edant;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

       btnsalvar = (Button)findViewById(R.id.btnsalvar);
       btnsalvar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
                String nome = ednome.getText().toString();

                String tipo = edar.getText().toString();
                String local = edloc.getText().toString();
                String anotacao = edant.getText().toString();

           }
       });
    }
}