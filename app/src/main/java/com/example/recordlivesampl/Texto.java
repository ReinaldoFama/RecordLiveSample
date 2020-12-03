package com.example.recordlivesampl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.w3c.dom.Text;

public class Texto extends AppCompatActivity {
    Button btnsalvar;
    EditText ednome, edar, edloc, edant;
    String st1, st2, st3, st4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_texto);

       btnsalvar = (Button)findViewById(R.id.btnsalvar);
       ednome = (EditText)findViewById(R.id.ednome);
       edar = (EditText)findViewById(R.id.edar);
       edloc = (EditText)findViewById(R.id.edloc);
       edant = (EditText)findViewById(R.id.edant);
       btnsalvar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent i = new Intent(Texto.this, Info.class);
               st1=ednome.getText().toString();
               i.putExtra("Value", st1);
               st2=edar.getText().toString();
               i.putExtra("Value2", st2);
               st3=edloc.getText().toString();
               i.putExtra("Value3", st3);
               st4=edant.getText().toString();
               i.putExtra("Value4", st4);
               startActivity(i);
               finish();

           }
       });
    }
}