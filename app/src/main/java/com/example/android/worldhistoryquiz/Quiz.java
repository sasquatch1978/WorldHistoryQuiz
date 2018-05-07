package com.example.android.worldhistoryquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Quiz extends AppCompatActivity implements View.OnClickListener {

    private int score;
    private TextView toastText;
    private View layout;
    private String userName;
    private ScrollView scrollQuiz;
    private Handler handler = new Handler();

    private Button submitQuestion1;
    private Button submitQuestion2;
    private Button submitQuestion3;
    private Button submitQuestion4;
    private Button submitQuestion5;
    private Button submitQuestion6;
    private Button submitQuestion7;
    private Button submitQuestion8;
    private Button submitQuestion9;
    private Button submitQuestion10;

    private RadioGroup groupQuestion1;
    private RadioGroup groupQuestion2;
    private RadioGroup groupQuestion3;
    private RadioGroup groupQuestion5;
    private RadioGroup groupQuestion7;
    private RadioGroup groupQuestion9;
    private RadioGroup groupQuestion10;

    private RadioButton question1B;
    private RadioButton question2C;
    private RadioButton question3A;
    private RadioButton question5B;
    private RadioButton question7A;
    private RadioButton question9C;
    private RadioButton question10D;

    private CheckBox question4A;
    private CheckBox question4B;
    private CheckBox question4C;
    private CheckBox question4D;
    private CheckBox question6A;
    private CheckBox question6B;
    private CheckBox question6C;
    private CheckBox question6D;

    private EditText question8Answer;

    static String totalScore = "totalScore";
    static String submitOne = "submitOne";
    static String submitTwo = "submitTwo";
    static String submitThree = "submitThree";
    static String submitFour = "submitFour";
    static String submitFive = "submitFive";
    static String submitSix = "submitSix";
    static String submitSeven = "submitSeven";
    static String submitEight = "submitEight";
    static String submitNine = "submitNine";
    static String submitTen = "submitTen";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz);

        /* Hide the keyboard until it is needed.
           Reference: https://stackoverflow.com/questions/7009096/disable-software-keyboard-in-android-until-edittext-is-chosen
           Date: 4/30/18
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Initialize views and set their listeners.
        initializeViews();

        // Retrieve info from intent.
        userName = getIntent().getStringExtra("name");
    }

    // Save variables
    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        // Score
        savedInstanceState.putInt(totalScore, score);
        // Buttons
        savedInstanceState.putBoolean(submitOne, submitQuestion1.isEnabled());
        savedInstanceState.putBoolean(submitTwo, submitQuestion2.isEnabled());
        savedInstanceState.putBoolean(submitThree, submitQuestion3.isEnabled());
        savedInstanceState.putBoolean(submitFour, submitQuestion4.isEnabled());
        savedInstanceState.putBoolean(submitFive, submitQuestion5.isEnabled());
        savedInstanceState.putBoolean(submitSix, submitQuestion6.isEnabled());
        savedInstanceState.putBoolean(submitSeven, submitQuestion7.isEnabled());
        savedInstanceState.putBoolean(submitEight, submitQuestion8.isEnabled());
        savedInstanceState.putBoolean(submitNine, submitQuestion9.isEnabled());
        savedInstanceState.putBoolean(submitTen, submitQuestion10.isEnabled());
        super.onSaveInstanceState(savedInstanceState);
    }

    // Retrieve variables
    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        // Score
        score = savedInstanceState.getInt(totalScore);
        // Buttons
        submitQuestion1.setEnabled(savedInstanceState.getBoolean(submitOne));
        submitQuestion2.setEnabled(savedInstanceState.getBoolean(submitTwo));
        submitQuestion3.setEnabled(savedInstanceState.getBoolean(submitThree));
        submitQuestion4.setEnabled(savedInstanceState.getBoolean(submitFour));
        submitQuestion5.setEnabled(savedInstanceState.getBoolean(submitFive));
        submitQuestion6.setEnabled(savedInstanceState.getBoolean(submitSix));
        submitQuestion7.setEnabled(savedInstanceState.getBoolean(submitSeven));
        submitQuestion8.setEnabled(savedInstanceState.getBoolean(submitEight));
        submitQuestion9.setEnabled(savedInstanceState.getBoolean(submitNine));
        submitQuestion10.setEnabled(savedInstanceState.getBoolean(submitTen));
        // Set the correct text color.
        setButtonTextState();
    }

    // Crossfade activity transition when the back button is pressed.
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        /* Crossfade the activity transition.
           Reference: https://stackoverflow.com/questions/18475826/how-to-perform-a-fade-animation-on-activity-transition
           Date: 3/18/18
         */
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }

    // Perform action on click.
    public void onClick(View v) {
        switch (v.getId()) {
            // Question one
            case R.id.submitQuestion1:
                // Correct answer.
                if (question1B.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion1.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.oneWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion1.setEnabled(false);
                submitQuestion1.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(submitQuestion2, submitQuestion2);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question two
            case R.id.submitQuestion2:
                // Correct answer.
                if (question2C.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion2.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.twoWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion2.setEnabled(false);
                submitQuestion2.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(groupQuestion3, submitQuestion3);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question three
            case R.id.submitQuestion3:
                // Correct answer.
                if (question3A.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion3.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.threeWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion3.setEnabled(false);
                submitQuestion3.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(question4A, submitQuestion4);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question four
            case R.id.submitQuestion4:
                // Correct Answer
                if (question4A.isChecked() && question4C.isChecked() && !question4B.isChecked() && !question4D.isChecked()) {
                    scoreTwoPoints();
                }

                // Do nothing until two answers are selected.
                else if ((!question4A.isChecked() && !question4B.isChecked() && !question4C.isChecked() && !question4D.isChecked()) ||
                        (question4A.isChecked() && !question4B.isChecked() && !question4C.isChecked() && !question4D.isChecked()) ||
                        (!question4A.isChecked() && question4B.isChecked() && !question4C.isChecked() && !question4D.isChecked()) ||
                        (!question4A.isChecked() && !question4B.isChecked() && question4C.isChecked() && !question4D.isChecked()) ||
                        (!question4A.isChecked() && !question4B.isChecked() && !question4C.isChecked() && question4D.isChecked())) {
                    selectTwoAnswersToast();
                    return;
                }

                // Wrong Answer
                else {
                    toastText.setText(R.string.fourWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion4.setEnabled(false);
                submitQuestion4.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(groupQuestion5, submitQuestion5);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question five
            case R.id.submitQuestion5:
                // Correct answer.
                if (question5B.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion5.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.fiveWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion5.setEnabled(false);
                submitQuestion5.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Disable the submit button.
                submitQuestion4.setEnabled(false);
                submitQuestion4.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(question6A, submitQuestion6);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question six
            case R.id.submitQuestion6:
                // Correct Answer
                if (question6A.isChecked() && question6D.isChecked() && !question6B.isChecked() && !question6C.isChecked()) {
                    scoreTwoPoints();
                }

                // Do nothing until two answers are selected.
                else if ((!question6A.isChecked() && !question6B.isChecked() && !question6C.isChecked() && !question6D.isChecked()) ||
                        (question6A.isChecked() && !question6B.isChecked() && !question6C.isChecked() && !question6D.isChecked()) ||
                        (!question6A.isChecked() && question6B.isChecked() && !question6C.isChecked() && !question6D.isChecked()) ||
                        (!question6A.isChecked() && !question6B.isChecked() && question6C.isChecked() && !question6D.isChecked()) ||
                        (!question6A.isChecked() && !question6B.isChecked() && !question6C.isChecked() && question6D.isChecked())) {
                    selectTwoAnswersToast();
                    return;
                }

                // Wrong Answer
                else {
                    toastText.setText(R.string.sixWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion6.setEnabled(false);
                submitQuestion6.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(groupQuestion7, submitQuestion7);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question seven
            case R.id.submitQuestion7:
                // Correct answer.
                if (question7A.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion7.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.sevenWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion7.setEnabled(false);
                submitQuestion7.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(question8Answer, submitQuestion8);
                        scrollQuiz.scrollBy(0, 100);
                        // Resolves having to double tap to open keyboard after scrolling.
                        question8Answer.requestFocus();
                    }
                }, 2000);
                break;

            // Question eight
            case R.id.submitQuestion8:
                String answer = question8Answer.getText().toString().trim();
                // Correct answer.
                if (answer.equalsIgnoreCase(getString(R.string.eightAnswer))) {
                    correctToast();
                    score += 3;
                }

                // Do nothing until an answer is entered.
                else if (answer.equals("")) {
                    toastText.setText(R.string.enterAnswer);
                    toast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.eightWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion8.setEnabled(false);
                submitQuestion8.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(groupQuestion9, submitQuestion9);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question nine
            case R.id.submitQuestion9:
                // Correct answer.
                if (question9C.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion9.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.nineWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion9.setEnabled(false);
                submitQuestion9.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to next question with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.requestChildFocus(groupQuestion10, submitQuestion10);
                        scrollQuiz.scrollBy(0, 100);
                    }
                }, 2000);
                break;

            // Question ten
            case R.id.submitQuestion10:
                // Correct answer.
                if (question10D.isChecked()) {
                    scoreOnePoint();
                }

                // Do nothing until an answer is selected.
                else if (groupQuestion10.getCheckedRadioButtonId() == -1) {
                    selectAnswerToast();
                    return;
                }

                // Wrong answer.
                else {
                    toastText.setText(R.string.tenWrong);
                    toast();
                }

                // Disable the submit button.
                submitQuestion10.setEnabled(false);
                submitQuestion10.setTextColor(getResources().getColor(R.color.disabledButtonText));

                // Scroll to Results button with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        scrollQuiz.fullScroll(View.FOCUS_DOWN);
                    }
                }, 2000);
                break;

            // Results button
            case R.id.resultsButton:
                toastText.setText(getString(R.string.resultToast, score));
                toast();

                // Advance to Results with a two second delay.
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Intent intent = new Intent(Quiz.this, Results.class);
                        intent.putExtra("score", String.valueOf(score));
                        intent.putExtra("name", userName);
                        startActivity(intent);
                        /* Crossfade the activity transition.
                           Reference: https://stackoverflow.com/questions/18475826/how-to-perform-a-fade-animation-on-activity-transition
                           Date: 3/18/18
                         */
                        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                        finish();
                    }
                }, 2000);
                break;

            default:
                break;
        }
    }

    // Initialize views and set their listeners.
    public void initializeViews() {
        // Buttons
        submitQuestion1 = findViewById(R.id.submitQuestion1);
        submitQuestion1.setOnClickListener(this);
        submitQuestion2 = findViewById(R.id.submitQuestion2);
        submitQuestion2.setOnClickListener(this);
        submitQuestion3 = findViewById(R.id.submitQuestion3);
        submitQuestion3.setOnClickListener(this);
        submitQuestion4 = findViewById(R.id.submitQuestion4);
        submitQuestion4.setOnClickListener(this);
        submitQuestion5 = findViewById(R.id.submitQuestion5);
        submitQuestion5.setOnClickListener(this);
        submitQuestion6 = findViewById(R.id.submitQuestion6);
        submitQuestion6.setOnClickListener(this);
        submitQuestion7 = findViewById(R.id.submitQuestion7);
        submitQuestion7.setOnClickListener(this);
        submitQuestion8 = findViewById(R.id.submitQuestion8);
        submitQuestion8.setOnClickListener(this);
        submitQuestion9 = findViewById(R.id.submitQuestion9);
        submitQuestion9.setOnClickListener(this);
        submitQuestion10 = findViewById(R.id.submitQuestion10);
        submitQuestion10.setOnClickListener(this);
        Button resultsButton = findViewById(R.id.resultsButton);
        resultsButton.setOnClickListener(this);
        // RadioGroups
        groupQuestion1 = findViewById(R.id.groupQuestion1);
        groupQuestion2 = findViewById(R.id.groupQuestion2);
        groupQuestion3 = findViewById(R.id.groupQuestion3);
        groupQuestion5 = findViewById(R.id.groupQuestion5);
        groupQuestion7 = findViewById(R.id.groupQuestion7);
        groupQuestion9 = findViewById(R.id.groupQuestion9);
        groupQuestion10 = findViewById(R.id.groupQuestion10);
        // RadioButtons
        question1B = findViewById(R.id.question1B);
        question2C = findViewById(R.id.question2C);
        question3A = findViewById(R.id.question3A);
        question5B = findViewById(R.id.question5B);
        question7A = findViewById(R.id.question7A);
        question9C = findViewById(R.id.question9C);
        question10D = findViewById(R.id.question10D);
        // CheckBoxes
        question4A = findViewById(R.id.question4A);
        question4B = findViewById(R.id.question4B);
        question4C = findViewById(R.id.question4C);
        question4D = findViewById(R.id.question4D);
        question6A = findViewById(R.id.question6A);
        question6B = findViewById(R.id.question6B);
        question6C = findViewById(R.id.question6C);
        question6D = findViewById(R.id.question6D);
        // EditText
        question8Answer = findViewById(R.id.question8Answer);
        scrollQuiz = findViewById(R.id.scrollQuiz);

        // Custom Toast
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
        toastText = layout.findViewById(R.id.toastText);
    }

    // Set the button text color to disabled color if disabled on rotation.
    public void setButtonTextState() {
        if (!submitQuestion1.isEnabled()) {
            submitQuestion1.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion2.isEnabled()) {
            submitQuestion2.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion3.isEnabled()) {
            submitQuestion3.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion4.isEnabled()) {
            submitQuestion4.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion5.isEnabled()) {
            submitQuestion5.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion6.isEnabled()) {
            submitQuestion6.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion7.isEnabled()) {
            submitQuestion7.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion8.isEnabled()) {
            submitQuestion8.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion9.isEnabled()) {
            submitQuestion9.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }

        if (!submitQuestion10.isEnabled()) {
            submitQuestion10.setTextColor(getResources().getColor(R.color.disabledButtonText));
        }
    }

    // Define the toast.
    private void toast() {
        Toast toast = new Toast(this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 200);
        toast.setView(layout);
        toast.show();
    }

    // Display a toast if no radio button is selected.
    private void selectAnswerToast() {
        toastText.setText(R.string.selectAnswer);
        toast();
    }

    // Display a toast if two checkboxes aren't selected.
    private void selectTwoAnswersToast() {
        toastText.setText(R.string.twoAnswers);
        toast();
    }

    // Display a toast if the answer is correct.
    private void correctToast() {
        toastText.setText(R.string.correct);
        toast();
    }

    // Increase score 1 point for correct answer and display toast "Correct".
    private void scoreOnePoint() {
        score++;
        correctToast();
    }

    // Increase score 2 points for correct answer and display toast "Correct".
    private void scoreTwoPoints() {
        score += 2;
        correctToast();
    }
}
