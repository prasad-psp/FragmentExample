package com.psp.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.psp.fragmentexample.fragments.FragmentOne;
import com.psp.fragmentexample.fragments.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    Button btnOne,btnTwo;
    FrameLayout frameLayout;

    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo = new FragmentTwo();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMsg("onDestroy");
    }

    @Override
    protected void onStop() {
        super.onStop();
        logMsg("onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        logMsg("onPause");
    }

    @Override
    protected void onResume() {
        super.onResume();
        logMsg("onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMsg("onStart");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        logMsg("onCreate");

        init();

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void init() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
    }

    private void logMsg(String msg) {
        Log.d("FragmentExample.Main",msg);
    }
}