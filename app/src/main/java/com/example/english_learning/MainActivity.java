package com.example.english_learning;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void Learn(View view) {
        Intent intent = new Intent(this, LearnActivity.class);
        startActivity(intent);
        System.exit(0);
    }

    public void Exit(View view) {
        //android.os.Process.killProcess(android.os.Process.myPid());   //获取PID
        System.exit(0);
    }
}
