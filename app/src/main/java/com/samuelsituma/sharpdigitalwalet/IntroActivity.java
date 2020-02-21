package com.samuelsituma.sharpdigitalwalet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.stephentuso.welcome.WelcomeHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class IntroActivity extends AppCompatActivity {
    private static final int REQUEST_WELCOME_SCREEN_RESULT = 13;
    private WelcomeHelper sampleWelcomeScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // The welcome screen for this app (only one that automatically shows)
        sampleWelcomeScreen = new WelcomeHelper(this, SampleWelcomeActivity.class);
        sampleWelcomeScreen.show(savedInstanceState);

        //List of welcome screens
      //  welcomeScreens.add(new ScreenItem(R.string.title_sample, R.string.description_sample, sampleWelcomeScreen, null));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== REQUEST_WELCOME_SCREEN_RESULT){
            if(resultCode==RESULT_OK){
                Toast.makeText(getApplicationContext(), "Completed (RESULT_OK)", Toast.LENGTH_SHORT).show();

            }
            else if(resultCode==RESULT_CANCELED){
                Toast.makeText(getApplicationContext(), "Canceled (RESULT_CANCELED)", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        // This is needed to prevent welcome screens from being
        // automatically shown multiple times

        // This is the only one needed because it is the only one that
        // is shown automatically. The others are only force shown.
        sampleWelcomeScreen.onSaveInstanceState(outState);
    }


    private class ScreenItem {

        String title;
        String description;
        WelcomeHelper helper;
        Integer requestCode;

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass) {
            this(titleRes, descriptionRes, activityClass, null);
        }

        ScreenItem(int titleRes, int descriptionRes, Class<? extends WelcomeActivity> activityClass, Integer requestCode) {
          //this(titleRes, descriptionRes, new WelcomeHelper(IntroActivity.this,activityClass), requestCode);
        }

        ScreenItem(int titleRes, int descriptionRes, WelcomeHelper helper, Integer requestCode) {
            this.title = getString(titleRes);
            this.description = getString(descriptionRes);
            this.helper = helper;
            this.requestCode = requestCode;
        }

    }
}
