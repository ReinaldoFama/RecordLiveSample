package com.example.recordlivesampl;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Foto extends AppCompatActivity {
    private ImageButton next2;
    private ImageView imagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);

         retiraFoto();

        next2 = (ImageButton)findViewById(R.id.next2);

        next2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                next2Activity();
                
            }
        });
    }
    public void Send(View view){
        Intent i = new Intent(Foto.this, Info.class);
        i.putExtra("next2", R.id.ivImagem);
        startActivity(i);

    }

    private void next2Activity() {
        startActivity(new Intent(Foto.this, Texto.class));
    }

    private void retiraFoto(){
        imagem = (ImageView)findViewById(R.id.ivImagem);
        Button botaoFoto;
        botaoFoto = (Button)findViewById(R.id.btnimagem);
        botaoFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, 0);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();
        if (data != null){
            Bitmap bitmap = (Bitmap)bundle.get("data");
            imagem.setImageBitmap(bitmap);
        }

    }



}