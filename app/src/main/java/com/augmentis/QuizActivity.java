package com.augmentis.aypquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {

    Button trueButton;
    Button falseButton;

    Button nextButton;
    TextView questionText;

    Question[] question = new Question[]{
            new Question(R.string.question_1_text, true),
            new Question(R.string.question_2_nile, true),
            new Question(R.string.question_3_nile, false),
            new Question(R.string.question_4_nile, false),

    };

    int currentIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        falseButton = (Button) findViewById (R.id.false_button);
        trueButton = (Button) findViewById (R.id.true_button);
        nextButton = (Button) findViewById(R.id.next_button);
        questionText = (TextView) findViewById(R.id.text_question);

        currentIndex = 0;
        questionText.setText(question[currentIndex].getQuestionId());

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

                    updateQuestion();

//                if(currentIndex < (question.length - 1)) { //This function is also work na ka
//                    currentIndex ++;
//                }else {
//                    currentIndex = 0;
//                }
//                questionText.setText(question[currentIndex].getQuestionId());

            }
        });
    }

    public void updateQuestion(){
        questionText.setText(question[currentIndex].getQuestionId());
    }


    public void checkAnswer(boolean answer){
        boolean correctAnswer = question[currentIndex].getAnswer();

        int result = (answer == correctAnswer)? R.string.correct_text : R.string.incorrect_text;
        Toast.makeText(QuizActivity.this, result, Toast.LENGTH_SHORT) .show();

//        int result;
//        if(answer == correctAnswer){  // This function is also work na ka
//           result =  R.string.correct_text;
//        }else
//           result = R.string.incorrect_text;
    }

}