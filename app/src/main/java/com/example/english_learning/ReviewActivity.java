package com.example.english_learning;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    private String[] wrong_words = new String[30];
    private String[] wrong_means = new String[30];

    static private int Word_num = 0;
    static private int Mean_num = 0;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent=getIntent();
        wrong_words=intent.getStringArrayExtra("word");
        wrong_means=intent.getStringArrayExtra("mean");
        ((TextView) findViewById(R.id.textView_wrong)).setText(wrong_words[Word_num]);
    }

    public void Forget(View view) {
        ((TextView) findViewById(R.id.textView_mean)).setText(wrong_means[Mean_num]);
    }

    public void Remember(View view){
        Refresh();
    }

    public void Refresh() {
        Word_num += 1;
        Mean_num += 1;
        if(wrong_means[Mean_num]==null||wrong_means[Mean_num]=="")
        {
            Toast.makeText(this, "Congratulations,You finished reviewing!", Toast.LENGTH_SHORT).show();
        }
        else

            ((TextView) findViewById(R.id.textView_wrong)).setText(wrong_words[Word_num]);
    }


}
