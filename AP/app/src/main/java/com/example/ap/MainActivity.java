package com.example.ap;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.ap.ui.main.MainFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
    }
}