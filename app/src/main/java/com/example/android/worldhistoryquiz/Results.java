package com.example.android.worldhistoryquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Results extends AppCompatActivity implements View.OnClickListener {

    private String userName;
    private View layout;
    private TextView toastText;
    private TextView correct;
    private TextView percent;
    private TextView results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.results);

        // Initialize views and set their listeners.
        initializeViews();

        // Retrieve info from intent.
        String score = getIntent().getStringExtra("score");
        int totalPointsScored = Integer.parseInt(String.valueOf(score));
        userName = getIntent().getStringExtra("name");

        // Calculate percentage for number correct.
        int percentScored = totalPointsScored * 100 / 14;

        percent.setText(getString(R.string.percentCorrect, percentScored)); // Percentage text
        correct.setText(getString(R.string.numberCorrect, totalPointsScored)); // Number correct

        // Tells user how they did.
        if (percentScored == 100) {
            results.setText(getString(R.string.outstanding, userName));
        } else if (percentScored >= 80) {
            results.setText(getString(R.string.good, userName));
        } else if (percentScored >= 60) {
            results.setText(getString(R.string.ok, userName));
        } else {
            results.setText(getString(R.string.notGood, userName));
        }
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
            // Retry the quiz.
            case R.id.retryButton:
                /* Show a good luck toast, delayed 1/10 of a second, when next activity starts.
           This is here instead of in onCreate in Quiz.java so that it doesn't display every time the device is rotated.
         */
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        toastText.setText(getString(R.string.goodLuck, userName));
                        Toast toast = new Toast(getApplication());
                        toast.setDuration(Toast.LENGTH_SHORT);
                        toast.setGravity(Gravity.CENTER, 0, 200);
                        toast.setView(layout);
                        toast.show();
                    }
                }, 100);

                Intent intent = new Intent(Results.this, Quiz.class);
                intent.putExtra("name", userName);
                startActivity(intent);
                /* Crossfade the activity transition.
                   Reference: https://stackoverflow.com/questions/18475826/how-to-perform-a-fade-animation-on-activity-transition
                   Date: 3/18/18
                 */
                overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                finish();
                break;

            /* Share with Google+.
               Reference: https://stackoverflow.com/questions/12675105/sharing-to-google-from-android
               Date: 2/27/18
             */
            case R.id.shareButton:
                Intent shareIntent = ShareCompat.IntentBuilder.from(Results.this)
                        .setType("text/plain")
                        .setText(getString(R.string.shareMessage) + correct.getText().toString())
                        .getIntent()
                        .setPackage("com.google.android.apps.plus");
                if (shareIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(shareIntent);
                }

            default:
                break;
        }
    }

    // Initialize views and set their listeners.
    public void initializeViews() {
        // Buttons
        Button retryButton = findViewById(R.id.retryButton);
        retryButton.setOnClickListener(this);
        Button shareButton = findViewById(R.id.shareButton);
        shareButton.setOnClickListener(this);
        // TextViews
        results = findViewById(R.id.results);
        percent = findViewById(R.id.percent);
        correct = findViewById(R.id.correct);

        // Custom Toast
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
        toastText = layout.findViewById(R.id.toastText);
    }
}
