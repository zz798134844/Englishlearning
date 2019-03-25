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
import java.util.Random;

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
    private String wrong_num="";
    private int[] num = new int[10];
    private int i =0;


    private int[] SelectAnswerId = {R.id.textView_a, R.id.textView_b, R.id.textView_c, R.id.textView_d};
    private int rand = 0;
    private int RightAnswerId = 0;

    Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            rand = new Random().nextInt(4);
            String str1=String.valueOf(rand);
            Log.d("rand:",str1);
            RightAnswerId = 2131165336+rand;
            int[] shuzu = new int[4];
            int n=new Random().nextInt(3)+1;
            if(rand==0){
                shuzu[0]=0;
                shuzu[1]=new Random().nextInt(3)+1;
                while(n==shuzu[1])
                    n=new Random().nextInt(3)+1;
                shuzu[2]=n;
                for(int i=1;i<4;i++)
                {
                    if(i!=shuzu[1]&&i!=shuzu[2])
                        shuzu[3]=i;
                }
            }
            if(rand==1){
                shuzu[1]=0;
                shuzu[0]=new Random().nextInt(3)+1;
                while(n==shuzu[0])
                    n=new Random().nextInt(3)+1;
                shuzu[2]=n;
                for(int i=1;i<4;i++)
                {
                    if(i!=shuzu[0]&&i!=shuzu[2])
                        shuzu[3]=i;
                }
            }
            if(rand==2){
                shuzu[2]=0;
                shuzu[0]=new Random().nextInt(3)+1;
                while(n==shuzu[0])
                    n=new Random().nextInt(3)+1;
                shuzu[1]=n;
                for(int i=1;i<4;i++)
                {
                    if(i!=shuzu[0]&&i!=shuzu[1])
                        shuzu[3]=i;
                }
            }
            if(rand==3){
                shuzu[3]=0;
                shuzu[0]=new Random().nextInt(3)+1;
                while(n==shuzu[0])
                    n=new Random().nextInt(3)+1;
                shuzu[1]=n;
                for(int i=1;i<4;i++)
                {
                    if(i!=shuzu[0]&&i!=shuzu[1])
                        shuzu[2]=i;
                }
            }
            String str2=String.valueOf(shuzu[0]);
            Log.d("a:",str2);
            String str3=String.valueOf(shuzu[1]);
            Log.d("b:",str3);
            String str4=String.valueOf(shuzu[2]);
            Log.d("c:",str4);
            String str5=String.valueOf(shuzu[3]);
            Log.d("d:",str5);
            ((TextView) findViewById(R.id.textView_num)).setText(Right_num);
            ((TextView) findViewById(R.id.textView_word)).setText(words[Word_num]);
            ((TextView) findViewById(R.id.textView_a)).setText(means[Mean_num+shuzu[0]]);
            ((TextView) findViewById(R.id.textView_b)).setText(means[Mean_num+shuzu[1]]);
            ((TextView) findViewById(R.id.textView_c)).setText(means[Mean_num+shuzu[2]]);
            ((TextView) findViewById(R.id.textView_d)).setText(means[Mean_num+shuzu[3]]);

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
        intent.putExtra("num",wrong_num);
        startActivity(intent);
        System.exit(0);
    }

    public void Skip(View view) {
        Toast.makeText(this, "skip!", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                num[i]=Word_num+1;
                i++;
                wrong_num=String.valueOf(i);
                String sql1 = "select word from Words where id="+(num[0])+" or id="+(num[1])+" or id="+(num[2])+" or id="+(num[3])+
                        " or id="+(num[4])+" or id="+(num[5])+" or id="+(num[6])+" or id="+(num[7])+" or id="+(num[8])+" or id="+(num[9]);
                wrong_words = Database.getUserInfoByName(sql1);
                String sql2 = "select mean from Words where id="+(num[0])+" or id="+(num[1])+" or id="+(num[2])+" or id="+(num[3])+
                        " or id="+(num[4])+" or id="+(num[5])+" or id="+(num[6])+" or id="+(num[7])+" or id="+(num[8])+" or id="+(num[9]);
                wrong_means = Database.getUserInfoByName(sql2);
            }
        }).start();
        Refresh();
    }

    public void Click(View view){
        CheckAnswers(view.getId());
    }


    private void CheckAnswers(int id){
        String str=String.valueOf(id);
                Log.d("id1",str);
        if(id == RightAnswerId){
            wrong_num=String.valueOf(i);
            addNum();
        }
        else{
            Toast.makeText(this, "wrong!", Toast.LENGTH_SHORT).show();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String str=String.valueOf(Word_num);
                    Log.d("id2",str);
                    num[i]=Word_num;
                    i++;
                    wrong_num=String.valueOf(i);
                    String sql1 = "select word from Words where id="+(num[0])+" or id="+(num[1])+" or id="+(num[2])+" or id="+(num[3])+
                            " or id="+(num[4])+" or id="+(num[5])+" or id="+(num[6])+" or id="+(num[7])+" or id="+(num[8])+" or id="+(num[9]);
                    Log.d("id",sql1);
                    wrong_words = Database.getUserInfoByName(sql1);
                    String sql2 = "select mean from Words where id="+(num[0])+" or id="+(num[1])+" or id="+(num[2])+" or id="+(num[3])+
                            " or id="+(num[4])+" or id="+(num[5])+" or id="+(num[6])+" or id="+(num[7])+" or id="+(num[8])+" or id="+(num[9]);
                    wrong_means = Database.getUserInfoByName(sql2);
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
            intent.putExtra("num",wrong_num);
            startActivity(intent);
            System.exit(0);
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
