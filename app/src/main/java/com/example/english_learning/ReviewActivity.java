package com.example.english_learning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    private String[] wrong_words = new String[10];
    private String[] wrong_means = new String[10];
    private String wrong_num="0";
    private int wrong_n=0;

    static private int Word_num = 0;
    static private int Mean_num = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent=getIntent();
        wrong_words=intent.getStringArrayExtra("word");
        wrong_means=intent.getStringArrayExtra("mean");
        wrong_num=intent.getStringExtra("num");
        if (wrong_words[Word_num]==null||wrong_words[Word_num]=="") {
            Toast.makeText(this, "Congratulations,You all right!", Toast.LENGTH_SHORT).show();
        }else
        ((TextView) findViewById(R.id.textView_wrong)).setText(wrong_words[Word_num]);
        //wrong_words = new String[wrong_n];
        //wrong_means = new String[wrong_n];
    }

    public void Forget(View view) {
        ((TextView) findViewById(R.id.textView_mean)).setText(wrong_means[Mean_num]);
    }

    public void Remember(View view){

        ((TextView) findViewById(R.id.textView_mean)).setText("click forget show mean");
        Refresh();
    }

    public void Refresh() {
        Word_num += 1;
        Mean_num += 1;
        String str;
        str = String.valueOf(Word_num);
        Log.d("word_num:", str);
        Log.d("wrong_num:", wrong_num);
        wrong_n = Integer.parseInt(wrong_num);
        if (Word_num == wrong_n-1) {
            Toast.makeText(this, "Congratulations,You finished reviewing!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            System.exit(0);
        }
        else
            ((TextView) findViewById(R.id.textView_wrong)).setText(wrong_words[Word_num]);
    }

}
