package com.samuelsituma.sharpdigitalwalet;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth mAuth;
    EditText emailField;
    EditText passwordField;
    ProgressBar progressBar;
    TextView loginText;


    public void userSignup() {
        String email = emailField.getText().toString().trim();
        String password = passwordField.getText().toString().trim();
        if (email.isEmpty()) {
            emailField.setError("Email required");
            emailField.requestFocus();
            return;

        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            emailField.setError("Enter a valid email");
            emailField.requestFocus();
            return;
        }
        if (password.isEmpty()) {
            passwordField.setError("Password Required");
            passwordField.requestFocus();
            return;
        }
        if (password.length() < 6) {
            passwordField.setError("Minimum length of password should be 6.");
            passwordField.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Signup Success", Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(SignUpActivity.this, SignUp2Activity.class));
                        } else {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getApplicationContext(), "You are already registered", Toast.LENGTH_SHORT).show();
                            task.getException();
                            Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        emailField=(EditText)findViewById(R.id.emailField);
        passwordField = (EditText) findViewById(R.id.passwordField);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        loginText = (TextView) findViewById(R.id.loginText);


        if(AppStatus.getInstance(this).isOnline()){
            Toast.makeText(this,"You are Online!!!",Toast.LENGTH_SHORT).show();

        }
        else{
            emailField.setFocusable(false);
            passwordField.setFocusable(false);

            Toast.makeText(this, "Please check your connection!!! And restart the app", Toast.LENGTH_LONG).show();
        }
        mAuth =FirebaseAuth.getInstance();
        findViewById(R.id.signupButton).setOnClickListener(this);
        findViewById(R.id.loginText).setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.loginText:
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
                break;
            case R.id.signupButton:
                userSignup();
                break;
        }
    }
}
