package com.example.e_collegeapp.ui;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.e_collegeapp.MainActivity;
import com.example.e_collegeapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Locale;

public class SplashActivity extends AppCompatActivity {
    FirebaseAuth auth;
    FirebaseUser user;

    TextToSpeech tts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        auth = FirebaseAuth.getInstance();
        tts();
        user = auth.getCurrentUser();
        if (user == null) {
            handler.sendEmptyMessageDelayed(101, 3000);
        } else {
            handler.sendEmptyMessageDelayed(201, 3000);
        }
    }
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 101){
                Intent intent = new Intent(SplashActivity.this, RegistrationActivity.class);
                startActivity(intent);
                finish();
            }else{
                Intent intent = new Intent(SplashActivity.this, AllCollegeActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };
    void tts(){
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(tts.getEngines().size() == 0){
                    Toast.makeText(SplashActivity.this, "Please enable TTS in your settings", Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    tts.setLanguage(Locale.US);
                    speak("Welcome To E-CollegeApp");

                }
            }
        });

    }
    private void speak(String s) {
        tts.speak(s,TextToSpeech.QUEUE_FLUSH,null);
    }
    @Override
    protected void onPause() {
        super.onPause();
        tts.shutdown();
    }
}
