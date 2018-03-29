package cloud.viyana.quizzy;

/**
 * Created by Rama41222 on 3/28/18.
 */

public class QuestionBank {

    private int mQuestionID;
    private boolean mAnswer;

    public QuestionBank(int questionID, boolean answer) {
        mQuestionID = questionID;
        mAnswer = answer;
    }

    public int getQuestionID() {
        return mQuestionID;
    }

    public void setQuestionID(int questionID) {
        mQuestionID = questionID;
    }

    public boolean isAnswer() {
        return mAnswer;
    }

    public void setAnswer(boolean answer) {
        mAnswer = answer;
    }
}
