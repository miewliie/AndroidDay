package com.augmentis.aypquiz;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends AppCompatActivity {

    boolean answer;
    Button confirmButton;
    TextView answerText;
    boolean isCheated;

    private static final String ANSWER_EXTRA_KEY = "ANSWER";
    private static final String CHEATED_EXTRA_KEY = "CHEATED";

    public static Intent createIntent(Context context, boolean answer){
        Intent intent = new Intent(context, CheatActivity.class);
        intent.putExtra(ANSWER_EXTRA_KEY, answer);
        return intent;
    }

    public static boolean wasCheated(Intent intent){
        return intent.getExtras().getBoolean(CHEATED_EXTRA_KEY);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cheat);

        answer = getIntent().getExtras().getBoolean(ANSWER_EXTRA_KEY);
        answerText = (TextView) findViewById(R.id.text_answer);
//        Intent intent = getIntent();
//        Bundle extra = intent.getExtras();
//        answer = extra.getBoolean(ANSWER_EXTRA_KEY);
//        isCheated = false;

//        answer = getIntent().getExtras().getBoolean("NAME");

        confirmButton = (Button) findViewById(R.id.confirm_button);
        answerText = (TextView) findViewById(R.id.text_answer);

        confirmButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                if(answer){
                    answerText.setText(R.string.answer_is_true);
                }else {
                    answerText.setText("Answer is False !");
                }
                isCheated = true;
                returnResult();
            }
        });
    }

    private void returnResult() {
            Intent newIntent = new Intent();
            newIntent.putExtra(CHEATED_EXTRA_KEY, isCheated);
            setResult(RESULT_OK, newIntent );

    }
}
