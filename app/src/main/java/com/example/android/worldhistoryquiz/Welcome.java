package com.example.android.worldhistoryquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Welcome extends AppCompatActivity {

    private EditText name;
    private TextView toastText;
    private View layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        /* Hide the keyboard until it is needed.
           Reference: https://stackoverflow.com/questions/7009096/disable-software-keyboard-in-android-until-edittext-is-chosen
           Date: 4/30/18
         */
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        // Initialize views and set their listeners.
        initializeViews();
    }

    // Start the ScoreInfo activity.
    private View.OnClickListener beginQuiz = new View.OnClickListener() {
        public void onClick(View v) {
            String userName = name.getText().toString();
            // Check to see if a name is entered.
            if (userName.equals("")) {
                // Display message.
                toastText.setText(R.string.enterName);
                Toast toast = new Toast(getApplication());
                toast.setDuration(Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.CENTER, 0, 0);
                toast.setView(layout);
                toast.show();
                return;
            }

            Intent intent = new Intent(Welcome.this, ScoreInfo.class);
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
        Button beginButton = findViewById(R.id.beginButton);
        beginButton.setOnClickListener(beginQuiz);

        // Username EditText
        name = findViewById(R.id.name);

        // Custom Toast
        LayoutInflater inflater = getLayoutInflater();
        layout = inflater.inflate(R.layout.toast, (ViewGroup) findViewById(R.id.toastLayout));
        toastText = layout.findViewById(R.id.toastText);
    }
}
