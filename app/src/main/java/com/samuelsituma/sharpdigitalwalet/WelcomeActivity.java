package com.samuelsituma.sharpdigitalwalet;

import android.os.Bundle;

import com.stephentuso.welcome.WelcomeHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    WelcomeHelper welcomeScreen;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        welcomeScreen = new WelcomeHelper(this,SampleWelcomeActivity.class);
        welcomeScreen.show(savedInstanceState);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        welcomeScreen.onSaveInstanceState(outState);
    }
}
