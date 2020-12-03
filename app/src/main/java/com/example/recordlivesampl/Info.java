package com.example.recordlivesampl;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Info extends AppCompatActivity {

    TextView info1, info2, info3, info4;
    String st1, st2, st3, st4;
    private ImageView imagem ;
    private final int GALERIA_IMAGEMS = 1;
    private final int AUDIO = 2;
    private  Button btnPlay,btnStop;
    private  MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        btnPlay = (Button)findViewById(R.id.btnPlay);
        btnStop = (Button)findViewById(R.id.btnStop);
        Button audio = (Button)findViewById(R.id.btnPlay);



        imagem = (ImageView) findViewById(R.id.ivImagem);
        Button galeria = (Button)findViewById(R.id.btnImagem);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, GALERIA_IMAGEMS);

            }

        });

        info1=findViewById(R.id.info1);
        st1=getIntent().getExtras().getString("Value");
        info1.setText(st1);
        info2=findViewById(R.id.info2);
        st2=getIntent().getExtras().getString("Value2");
        info2.setText(st2);
        info3=findViewById(R.id.info3);
        st3=getIntent().getExtras().getString("Value3");
        info3.setText(st3);
        info4=findViewById(R.id.info4);
        st4=getIntent().getExtras().getString("Value4");
        info4.setText(st4);

    }
    @Override
    protected void onActivityResult (int requestCode,int resultCode, Intent data) {
        super.onActivityResult (requestCode,resultCode, data);

        if(resultCode == RESULT_OK && requestCode == GALERIA_IMAGEMS) {
            Uri selectedImage = data.getData();
            String[]filePath = {MediaStore.Images.Media.DATA};
            Cursor c = getContentResolver().query(selectedImage,filePath,null,null,null);
            c.moveToFirst  ();
            int columnIndex = c.getColumnIndex(filePath[0]);
            String picturePath = c.getString (columnIndex);
            c.close();
            Bitmap thumbnail = (BitmapFactory.decodeFile(picturePath));
            imagem.setImageBitmap(thumbnail);
        }
    }
    private void  clickPlay(){
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mediaPlayer==null){
                    mediaPlayer.create(Info.this,mediaPlayer.MEDIA_INFO_STARTED_AS_NEXT);
                    mediaPlayer.start();
                }else {
                    if (mediaPlayer.isPlaying()){
                        mediaPlayer.pause();
                    }else {
                        mediaPlayer.start();
                    }
                }

            }
        });
    }
    private void clickStop(){
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer!=null){
                    mediaPlayer.stop();
                }

            }
        });
    }
}