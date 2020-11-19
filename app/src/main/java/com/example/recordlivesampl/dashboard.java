package com.example.recordlivesampl;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.util.UUID;

public class dashboard extends AppCompatActivity {
    private WebView webView;
    ImageButton grvg, stpg, plar, stpr, next;
    String pathSave = "";
    MediaRecorder mediaRecorder;
    MediaPlayer mediaPlayer;

    final int REQUEST_PERMISSION_CODE = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        plar = (ImageButton)findViewById(R.id.plar);
        grvg = (ImageButton)findViewById(R.id.grvg);
        stpr = (ImageButton)findViewById(R.id.stpr);
        stpg = (ImageButton)findViewById(R.id.stpg);
        next = (ImageButton)findViewById(R.id.next);


        if(checkPermissionFromDevice())
        {
           grvg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   pathSave = Environment.getExternalStorageDirectory()
                           .getAbsolutePath()+"/"
                           + UUID.randomUUID().toString()+"_audio_record.3gp";
                   setupMediaRecorder();
                   try{
                       mediaRecorder.prepare();
                       mediaRecorder.start();
                   } catch(IOException e){
                       e.printStackTrace();
                   }

                   plar.setEnabled(false);
                   stpr.setEnabled(false);

                   Toast.makeText(dashboard.this, "GRAVANDO...", Toast.LENGTH_SHORT).show();

               }
           });

           stpg.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   mediaRecorder.stop();
                   stpg.setEnabled(false);
                   plar.setEnabled(true);
                   grvg.setEnabled(true);
                   stpr.setEnabled(false);
               }
           });

           plar.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   stpr.setEnabled(true);
                   stpg.setEnabled(false);
                   grvg.setEnabled(false);

                   mediaPlayer = new MediaPlayer();
                   try{
                       mediaPlayer.setDataSource(pathSave);
                       mediaPlayer.prepare();
                   } catch (IOException e) {
                       e.printStackTrace();
                   }
                   mediaPlayer.start();
                   Toast.makeText(dashboard.this, "REPRODUZINDO...", Toast.LENGTH_SHORT).show();
               }
           });

           stpr.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   stpr.setEnabled(false);
                   grvg.setEnabled(true);
                   stpr.setEnabled(false);
                   plar.setEnabled(true);

                   if (mediaPlayer != null)
                   {
                        mediaPlayer.stop();
                        mediaPlayer.release();
                        setupMediaRecorder();
                   }
               }
           });
        }
        else
        {
            requestPermission();
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextActivity();
            }
        });
    }

    private void nextActivity() {
        startActivity(new Intent(dashboard.this, Foto.class));
    }

    private void setupMediaRecorder() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
        mediaRecorder.setOutputFile(pathSave);
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.RECORD_AUDIO
        }, REQUEST_PERMISSION_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case REQUEST_PERMISSION_CODE:
             {
                 if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                     Toast.makeText(this, "Permisson Aprovada", Toast.LENGTH_SHORT).show();
                 else
                     Toast.makeText(this, "Permission Negada", Toast.LENGTH_SHORT).show();
             }
                break;

        }
    }

    private boolean checkPermissionFromDevice() {
        int write_external_storage_result = ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        int record_audio_result = ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO);
        return write_external_storage_result == PackageManager.PERMISSION_GRANTED &&
                record_audio_result == PackageManager.PERMISSION_GRANTED;
    }


}
