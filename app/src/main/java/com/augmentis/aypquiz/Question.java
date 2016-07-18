package com.augmentis.aypquiz;

/**
 * Created by Apinya on 7/14/2016.
 */
public class Question {
    private int questionId;
    private boolean answer;
    private boolean checkCheated;


    public Question(int questionId, boolean answer){
        this.questionId = questionId;
        this.answer = answer;
        this.checkCheated = false;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public boolean getAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }

    public boolean isCheckCheated() {
        return checkCheated;
    }

    public void setCheckCheated(boolean checkCheated) {
        this.checkCheated = checkCheated;
    }
}
