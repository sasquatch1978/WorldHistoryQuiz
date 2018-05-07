package com.example.android.worldhistoryquiz;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ScoreInfo extends AppCompatActivity {

    private String userName;
    private TextView toastText;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.score_info);

        // Retrieve info from intent.
        userName = getIntent().getStringExtra("name");

        // Initialize views and set their listeners.
        initializeViews();
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

    // Start the Quiz activity.
    private View.OnClickListener next = new View.OnClickListener() {
        public void onClick(View v) {
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

            Intent intent = new Intent(ScoreInfo.this, Quiz.class);
            intent.putExtra("name", userName);
            startActivity(intent);
        /* Crossfade the activity transition.
           Reference: https://stackoverflow.com/questions/18475826/how-to-perform-a-fade-animation-on-activity-transition
           Date: 3/18/18
         */
            overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        }
    };

    // Initialize views and set their listeners.
    public void initializeViews() {
        // Begin Button
        Button nextButton = findViewById(R.id.nextButton);
        nextButton.setOnClickListener(next);

        // Custom Toast
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
        toastText = layout.findViewById(R.id.toastText);
    }
}
