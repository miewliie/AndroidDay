package com.augmentis.aypquiz;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    private static final int REQUEST_CHEATED = 269 ;
    Button trueButton;
    Button falseButton;
    Button previousButton;
    Button nextButton;
    TextView questionText;
    Button cheatButton;


    Question[] question = new Question[]{
            new Question(R.string.question_1_text, true),
            new Question(R.string.question_2_nile, true),
            new Question(R.string.question_3_nile, false),
            new Question(R.string.question_4_nile, false),

    };

    int currentIndex;


    private static final String TAG = "AYPQUIZ";
    private static final String INDEX = "INDEX";
    private boolean isCheater;

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "On Stop");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "On Start");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "On Resume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "On Pause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "On Destroy");
    }

    @Override
    protected void onSaveInstanceState(Bundle saveInstanceState) {
        super.onSaveInstanceState(saveInstanceState);
        Log.d(TAG, "State is saving");
        saveInstanceState.putInt(INDEX, currentIndex);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        falseButton = (Button) findViewById (R.id.false_button);
        trueButton = (Button) findViewById (R.id.true_button);
        nextButton = (Button) findViewById(R.id.next_button);
        previousButton = (Button) findViewById(R.id.previous_button);
        questionText = (TextView) findViewById(R.id.text_question);
        cheatButton = (Button) findViewById(R.id.cheat_button);

        questionText.setText(question[currentIndex].getQuestionId());

        if(savedInstanceState != null){
            currentIndex = savedInstanceState.getInt(INDEX, 0);
        }else {
            currentIndex = 0;
        }

        resetCheater();
        updateQuestion();


        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                currentIndex++;
                if(currentIndex == question.length) currentIndex = 0;

                resetCheater();
                updateQuestion();

//                if(currentIndex < (question.length - 1)) { //This function is also work na ka
//                    currentIndex ++;
//                }else {
//                    currentIndex = 0;
//                }
//                questionText.setText(question[currentIndex].getQuestionId());

            }
        });

        previousButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {

//                if(currentIndex == 0) {
//                    currentIndex   = question.length - 1;
//                }else {
//                    currentIndex --;
//                }

                currentIndex = (currentIndex + 1) % question.length;
                resetCheater();
                updateQuestion();
                            }

        });

        cheatButton.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                //
                Intent intent = CheatActivity.createIntent(QuizActivity.this, getCurrentAnswer());
//                intent.putExtra("NAME", question[currentIndex].getAnswer());
                startActivityForResult(intent, REQUEST_CHEATED);
            }
        });



        Log.d(TAG, "On Create");
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent dataInternt) {
        if(resultCode != Activity.RESULT_OK){
            return;
        }

        if(requestCode == REQUEST_CHEATED){
            if (dataInternt == null){
                return;
            }
        }
         isCheater = CheatActivity.wasCheated(dataInternt);
    }

    private boolean getCurrentAnswer(){
        return question[currentIndex].getAnswer();
    }

    private void resetCheater(){
        isCheater = false;
    }
    public void updateQuestion(){
        questionText.setText(question[currentIndex].getQuestionId());
    }


    public void checkAnswer(boolean answer){

        boolean correctAnswer = question[currentIndex].getAnswer();

        int result;

        if( isCheater){
            result = R.string.cheater_text;
        }else {
            if(answer == correctAnswer){
                result = R.string.correct_text;
            }else {
                result = R.string.incorrect_text;
            }
        }

        Toast.makeText(QuizActivity.this, result, Toast.LENGTH_SHORT) .show();

//        int result;
//        if(answer == correctAnswer){  // This function is also work na ka
//           result =  R.string.correct_text;
//        }else
//           result = R.string.incorrect_text;
    }

}
