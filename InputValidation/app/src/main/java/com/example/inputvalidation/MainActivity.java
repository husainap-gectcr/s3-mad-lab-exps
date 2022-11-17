package com.example.inputvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;
import android.util.Patterns;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    private TextInputEditText email;
    private TextInputEditText password;
    private TextInputEditText phone;

    private TextInputLayout emailContainer;
    private TextInputLayout passwordContainer;
    private TextInputLayout phoneContainer;

    private MaterialButton login;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email =  (TextInputEditText)  findViewById(R.id.login_email);
        password = (TextInputEditText) findViewById(R.id.login_password);
        phone = (TextInputEditText)   findViewById(R.id.login_phone);


        emailContainer =  (TextInputLayout)  findViewById(R.id.login_EmailContainer);
        passwordContainer = (TextInputLayout) findViewById(R.id.login_passwordContainer);
        phoneContainer = (TextInputLayout)   findViewById(R.id.login_phoneContainer);

        login = (MaterialButton)findViewById(R.id.login_button);

        password.setTooltipText("Minimum 8 characters, at-least one Upper case, one Digit, one Special character - @#$%^&+=");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Login Button Clicked", Toast.LENGTH_SHORT).show();
                login();
            }
        });

        emailInputTextOnFocusListener();
        passwordInputTextOnFocusListener();
        phoneInputTextOnFocusListener();
    }

    private String validPassword() {
        String passwordText = password.getText().toString();

        if (passwordText.length() < 8) {
            return "Minimum 8 Character Password";
        }
        if (!passwordText.matches(".*[A-Z].*")) {
            return "Must Contain 1 Upper-case Character";
        }
        if (!passwordText.matches(".*[0-9].*")) {
            return "Must Contain 1 digit";
        }
        if (!passwordText.matches(".*[a-z].*")) {
            return "Must Contain 1 Lower-case Character";
        }
        if (!passwordText.matches(".*[@#$%^&+=].*")) {
            return "Must Contain 1 Special Character (@#$%^&+=)";
        }

        return null;
    }

    private String validEmail() {

        String emailInputText = email.getText().toString();
        if(!Patterns.EMAIL_ADDRESS.matcher(emailInputText).matches()) {
            return "Invalid Email Address";
        } else {
            return null;
        }

    }

    private String validPhoneNumber() {
        String phoneText = phone.getText().toString();
        if (!phoneText.matches(".*[0-9].*")) {
            return "Must be all Digits";
        }
        if (phoneText.length() != 10) {
            return "Must be 10 Digits";
        }
        return null;
    }

    private void login() {

        emailContainer.setHelperText(validEmail());
        passwordContainer.setHelperText(validPassword());
        phoneContainer.setHelperText(validPhoneNumber());

        boolean checkIfEmailIsValid = emailContainer.getHelperText() == null;
        boolean validPassword = passwordContainer.getHelperText() == null;
        boolean checkValidPhoneNumber = phoneContainer.getHelperText() == null;

        if (checkIfEmailIsValid && validPassword && checkValidPhoneNumber) {
            Intent intent = new Intent(MainActivity.this, Dashboard.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetForm() {

        email.setText("");
        password.setText("");
        phone.setText("");

        passwordContainer.setHelperText("Required");
        emailContainer.setHelperText("Required");
        phoneContainer.setHelperText("Required");
    }

    private void passwordInputTextOnFocusListener() {
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    passwordContainer.setHelperText(validPassword());
                }
            }
        });
    }

    private void emailInputTextOnFocusListener() {
        email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    emailContainer.setHelperText(validEmail());
                }
            }
        });
    }

    private void phoneInputTextOnFocusListener() {

        phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    phoneContainer.setHelperText(validPhoneNumber());
                }
            }
        });
    }
}
