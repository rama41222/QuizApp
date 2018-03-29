package cloud.viyana.quizzy;

import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mTrueBtn;
    private Button mFalseBtn;
    private TextView mQuestionTextView;
    private ProgressBar mProgressBar;
    private TextView mScoreTextView;
    private int mIndex = 0;
    private int mQuestion;
    private int mScore;

    @NonNull
    private QuestionBank[]mQuestionBank = new QuestionBank[] {
            new QuestionBank(R.string.question_1, true),
            new QuestionBank(R.string.question_2, true),
            new QuestionBank(R.string.question_3, true),
            new QuestionBank(R.string.question_4, true),
            new QuestionBank(R.string.question_5, true),
            new QuestionBank(R.string.question_6, false),
            new QuestionBank(R.string.question_7, true),
            new QuestionBank(R.string.question_8, false),
            new QuestionBank(R.string.question_9, true),
            new QuestionBank(R.string.question_10, true),
            new QuestionBank(R.string.question_11, false),
            new QuestionBank(R.string.question_12, false),
            new QuestionBank(R.string.question_13, true),
    };

    final private int PROGRESSBAR_INCREMENT = (int) Math.ceil(100 / mQuestionBank.length);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTrueBtn = (Button) findViewById(R.id.true_btn);
        mFalseBtn = (Button) findViewById(R.id.false_btn);
        mQuestionTextView = (TextView) findViewById(R.id.question_text);
        mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mScoreTextView = (TextView) findViewById(R.id.score_text);
        mProgressBar.setMax(100);

        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);

        mTrueBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                updateQuestionId();
            }
        });

        mFalseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
                updateQuestionId();
            }
        });

    }

    private void checkAnswer(boolean answer) {
        boolean currentAnswer = mQuestionBank[mIndex].isAnswer();
        boolean isCorrect = currentAnswer == answer;
        if(isCorrect) {
            mScore ++;
            mScoreTextView.setText("Score " + mScore + "/" + mQuestionBank.length);
            Toast.makeText(getApplicationContext(), "You got it", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "Nope, Better luck next time!", Toast.LENGTH_SHORT).show();

        }

    }

    private void updateQuestionId() {
        mIndex = (mIndex +1 ) % mQuestionBank.length;

        if(mIndex == 0) {
            mProgressBar.setProgress(0);
            mScore = 0;

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Quiz Over");
            alert.setCancelable(false);
            alert.setMessage("You Scored " + mScore + " points!");
            alert.setPositiveButton("Quit", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alert.show();
        }

        if(mIndex == mQuestionBank.length-1) {
            mProgressBar.setProgress(100);
        }
        mQuestion = mQuestionBank[mIndex].getQuestionID();
        mQuestionTextView.setText(mQuestion);
        mProgressBar.incrementProgressBy(PROGRESSBAR_INCREMENT);


    }
}
