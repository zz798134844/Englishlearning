package com.example.english_learning;

import android.content.Intent;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import static com.example.english_learning.Database.getUserInfoByName;

public class LearnActivity extends AppCompatActivity {
    static private int Word_num = 0;
    static private int Mean_num = 0;
    static private String Right_num = "0";
    static private int Right_n = 0;
    private String[] words = new String[30];
    private String[] means = new String[30];

    private String[] wrong_words = new String[30];
    private String[] wrong_means = new String[30];

    private int[] SelectAnswerId = {R.id.textView_a, R.id.textView_b, R.id.textView_c, R.id.textView_d};

    private int RightAnswerId = 2131165333;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {

            ((TextView) findViewById(R.id.textView_num)).setText(Right_num);
            ((TextView) findViewById(R.id.textView_word)).setText(words[Word_num]);
            ((TextView) findViewById(R.id.textView_a)).setText(means[Mean_num+0]);
            ((TextView) findViewById(R.id.textView_b)).setText(means[Mean_num+1]);
            ((TextView) findViewById(R.id.textView_c)).setText(means[Mean_num+2]);
            ((TextView) findViewById(R.id.textView_d)).setText(means[Mean_num+3]);

            return false;}
    });


            protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql1 = "select word from Words";
                words = Database.getUserInfoByName(sql1);
                String sql2 = "select mean from Words";
                means = Database.getUserInfoByName(sql2);
                Message m = new Message();
                handler.sendMessage(m);
            }
        }).start();

    }
    public void Finish(View view) {
        Toast.makeText(this, "Congratulations,You answered "+Right_num+" questions correctly.let's start reviewing. ", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, ReviewActivity.class);
        intent.putExtra("word",wrong_words);
        intent.putExtra("mean",wrong_means);
        startActivity(intent);
    }

    public void Skip(View view) {
        Toast.makeText(this, "skip!", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                String sql1 = "select word from Words where id="+(Word_num+1)+"";
                wrong_words = Database.getUserInfoByName(sql1);
                String sql2 = "select mean from Words where id="+(Word_num+1)+"";
                wrong_means = Database.getUserInfoByName(sql2);
                Message m = new Message();
                handler.sendMessage(m);
            }
        }).start();
        Refresh();
    }

    public void Click(View view){
        CheckAnswers(view.getId());
    }


    private void CheckAnswers(int id){
        String str=String.valueOf(id);
                Log.d("asdsadasd",str);
        if(id == RightAnswerId){
            addNum();
        }
        else{
            Toast.makeText(this, "wrong!", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String sql1 = "select word from Words where id="+(Word_num+1)+"";
                    wrong_words = Database.getUserInfoByName(sql1);
                    String sql2 = "select mean from Words where id="+(Word_num+1)+"";
                    wrong_means = Database.getUserInfoByName(sql2);
                    Message m = new Message();
                    handler.sendMessage(m);
                }
            }).start();
        }
        Refresh();
    }

    private void Refresh(){
        Word_num += 1;
        Mean_num += 1;
        if(Word_num==10){
            Toast.makeText(this, "Congratulations,You answered "+Right_num+" questions correctly.let's start reviewing. ", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, ReviewActivity.class);
            intent.putExtra("word",wrong_words);
            intent.putExtra("mean",wrong_means);
            startActivity(intent);
        }
        Message m = new Message();
        handler.sendMessage(m);
    }



    static public void addNum(){
        Right_n=Integer.parseInt(Right_num);
        Right_n += 1;
        Right_num=String.valueOf(Right_n);
    }



}
