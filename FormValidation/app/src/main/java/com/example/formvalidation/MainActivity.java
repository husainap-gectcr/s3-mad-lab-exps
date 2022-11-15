package com.example.formvalidation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import com.example.formvalidation.databinding.ActivityMainBinding;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        //binding.setTemp(temperatureData); // generated setter based on the
        binding.loginButton.setOnClickListener(new View.OnClickListener() {
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
        String passwordText = binding.loginPassword.getText().toString();

        if (passwordText.length() < 8) {
            return "Minimum 8 Character Password";
        }
        if (!passwordText.matches(".*[A-Z].*")) {
            return "Must Contain 1 Upper-case Character";
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

        String emailInputText = binding.loginEmail.getText().toString();
        return null;

    }

    private String validPhoneNumber() {
        String phoneText = binding.loginPhone.getText().toString();
        if (!phoneText.matches(".*[0-9].*")) {
            return "Must be all Digits";
        }
        if (phoneText.length() != 10) {
            return "Must be 10 Digits";
        }
        return null;
    }

    private void login() {

        binding.loginEmailContainer.setHelperText(validEmail());
        binding.loginPasswordContainer.setHelperText(validPassword());
        binding.loginPhoneContainer.setHelperText(validPhoneNumber());

        boolean checkIfEmailIsValid = binding.loginEmailContainer.getHelperText() == null;
        boolean validPassword = binding.loginPasswordContainer.getHelperText() == null;
        boolean checkValidPhoneNumber = binding.loginPhoneContainer.getHelperText() == null;

        if (checkIfEmailIsValid && validPassword && checkValidPhoneNumber) {
            Toast.makeText(this, "Valid Form", Toast.LENGTH_SHORT).show();
            resetForm();
        } else {
            Toast.makeText(this, "Invalid Form", Toast.LENGTH_SHORT).show();
        }
    }

    private void resetForm() {

        binding.loginEmail.setText("");
        binding.loginPassword.setText("");
        binding.loginPhone.setText("");

        binding.loginPasswordContainer.setHelperText("Required");
        binding.loginEmailContainer.setHelperText("Required");
        binding.loginPhoneContainer.setHelperText("Required");
    }

    private void passwordInputTextOnFocusListener() {
        binding.loginPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    binding.loginPasswordContainer.setHelperText(validPassword());
                }
            }
        });
    }

    private void emailInputTextOnFocusListener() {
        binding.loginEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    binding.loginEmailContainer.setHelperText(validEmail());
                }
            }
        });
    }

    private void phoneInputTextOnFocusListener() {

        binding.loginPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus){
                    binding.loginPhoneContainer.setHelperText(validPhoneNumber());
                }
            }
        });
    }
}
