package com.psp.fragmentexample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.psp.fragmentexample.fragments.FragmentOne;
import com.psp.fragmentexample.fragments.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    Button btnOne,btnTwo;

    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo = new FragmentTwo();
    Fragment currentFragment = fragmentOne;

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

                if(!(currentFragment instanceof FragmentOne)) {

                    if(getSupportFragmentManager().findFragmentByTag("one") == null) {
                        //add fragment
                        addFragment(currentFragment,fragmentOne,"one");
                        currentFragment = fragmentOne;
                    }
                    else {
                        // show fragment
                        showFragment(currentFragment,fragmentOne);
                        currentFragment = fragmentOne;
                    }
                }
            }
        });

        btnTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(currentFragment instanceof FragmentTwo)) {
                    if(getSupportFragmentManager().findFragmentByTag("two") == null) {
                        // add frgament
                        addFragment(currentFragment,fragmentTwo,"two");
                        currentFragment = fragmentTwo;
                    }
                    else {
                        // show fragment
                        showFragment(currentFragment,fragmentTwo);
                        currentFragment = fragmentTwo;
                    }
                }
            }
        });
    }

    private void init() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);

        addFragment(currentFragment,fragmentOne,"one");
    }

    private void addFragment(Fragment currFragment,Fragment newFragment,String tag) {
        getSupportFragmentManager().beginTransaction()
                .hide(currFragment)
                .add(R.id.framelayout,newFragment,tag)
                .addToBackStack(tag)
                .show(newFragment)
                .commit();

        logMsg("addFragment currentFragment is "+currFragment+" and newFragment is "+newFragment);
    }

    private void showFragment(Fragment currFragment,Fragment showFragment) {
        getSupportFragmentManager().beginTransaction()
                .hide(currFragment)
                .show(showFragment)
                .commit();

        logMsg("showFragment currentFragment is "+currFragment+" and showFragment is "+showFragment);
    }

    private void logMsg(String msg) {
        Log.d("FragmentExample.Main",msg);
    }
}