package com.example.english_learning;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ReviewActivity extends AppCompatActivity {
    private String[] wrong_words = new String[]{};
    private String[] wrong_means = new String[]{};

    static private int Word_num = 0;
    static private int Mean_num = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        Intent intent=getIntent();
        wrong_words=intent.getStringArrayExtra("word");
        wrong_means=intent.getStringArrayExtra("mean");
        Refresh();
    }

    public void Forget(View view) {
        ((TextView) findViewById(R.id.textView_mean)).setText(wrong_means[Mean_num]);
        Refresh();
    }

    public void Remember(View view) {
        Refresh();
        Word_num++;
        if(wrong_means[Mean_num]==null||wrong_means[Mean_num]=="")
        {
            Toast.makeText(this, "Congratulations,You finished reviewing!", Toast.LENGTH_SHORT).show();
        }
    }

    public void Refresh() {
        ((TextView) findViewById(R.id.textView_word)).setText(wrong_words[Word_num]);
    }


}
