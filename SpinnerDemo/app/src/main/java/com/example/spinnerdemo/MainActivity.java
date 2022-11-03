package com.example.spinnerdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity  {

    private Spinner sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sp = (Spinner) findViewById(R.id.spinner);
        String country[]= {"India", "USA", "UK", "UAE","France"};
        ArrayAdapter adaptor = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, country);
        adaptor.setDropDownViewResource(android.R.layout.simple_spinner_item);
        sp.setAdapter(adaptor);
        sp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                 Toast.makeText(getApplicationContext(), country[position], Toast.LENGTH_LONG).show();
             }

             @Override
             public void onNothingSelected(AdapterView<?> adapterView) {

             }
         });

    }
}