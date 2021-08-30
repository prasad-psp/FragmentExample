package com.psp.fragmentexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.psp.fragmentexample.fragments.FragmentOne;
import com.psp.fragmentexample.fragments.FragmentTwo;

public class MainActivity extends AppCompatActivity {

    Button btnOne,btnTwo,btnShowFragment;

    FragmentOne fragmentOne = new FragmentOne();
    FragmentTwo fragmentTwo = new FragmentTwo();
    Fragment currentFragment = fragmentOne;

    private boolean onSaveInstanceRun = false;
    private boolean pendingFragmentShow = false;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        logMsg("onDestroy");
        System.exit(0);
    }

    @Override
    public void onBackPressed() {

        if(fragmentOne != null) {
            if(fragmentOne.isVisible()) {
                logMsg("back pressed when fragment one is visible");
                onDestroy();
            }
            else {
                logMsg("OnBack pressed fragment one is not visible");
                showFragment(currentFragment,fragmentOne);
                currentFragment = fragmentOne;
                clearBackStack();
            }
        }
        else {
            logMsg("back pressed when frament one is null");
        }
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

        if(pendingFragmentShow) {
            logMsg("pending fragment show is true");
            showFragment(currentFragment,fragmentOne);
            currentFragment = fragmentOne;
            pendingFragmentShow = false;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        logMsg("onStart");
        onSaveInstanceRun = false;
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        logMsg("onSaveInstanceState");
        onSaveInstanceRun = true;
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
                        currentFragment = fragmentOne;
                        addFragment(currentFragment,fragmentOne,"one");
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

        btnShowFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if(!onSaveInstanceRun) {
                            logMsg("btnShowFragment onSaveInstanceRun false");
                            showFragment(currentFragment, fragmentOne);
                            currentFragment = fragmentOne;
                        }
                        else {
                            logMsg("btnShowFragment onSaveInstanceRun true");
                            pendingFragmentShow = true;
                        }
                    }
                },5000);
            }
        });
    }

    private void init() {
        btnOne = findViewById(R.id.btnOne);
        btnTwo = findViewById(R.id.btnTwo);
        btnShowFragment = findViewById(R.id.btnShowFrag);

        addFragment(currentFragment,fragmentOne,"one");
    }

    private void addFragment(Fragment currFragment,Fragment newFragment,String tag) {
        getSupportFragmentManager().beginTransaction()
                .hide(currFragment)
                .add(R.id.framelayout,newFragment,tag)
                .addToBackStack(tag)
                .show(newFragment)
                .setMaxLifecycle(currFragment, Lifecycle.State.STARTED)
                .setMaxLifecycle(newFragment, Lifecycle.State.RESUMED)
                .commit();

        logMsg("addFragment currentFragment is "+currFragment+" and newFragment is "+newFragment);
    }

    private void showFragment(Fragment currFragment,Fragment showFragment) {

        if(currFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .hide(currFragment)
                    .setMaxLifecycle(currentFragment, Lifecycle.State.STARTED)
                    .show(showFragment)
                    .setMaxLifecycle(showFragment, Lifecycle.State.RESUMED)
                    .commit();

            logMsg("showFragment currentFragment is " + currFragment + " and showFragment is " + showFragment);
        }
        else {
            logMsg("current fragment is not added");
        }
    }

    private void clearBackStack(){
        if(getSupportFragmentManager().getBackStackEntryCount()>1) {

            FragmentManager.BackStackEntry entry = getSupportFragmentManager().getBackStackEntryAt(
                    1);
            getSupportFragmentManager().popBackStack(entry.getId(),
                    FragmentManager.POP_BACK_STACK_INCLUSIVE);
            getSupportFragmentManager().executePendingTransactions();
            logMsg("clear backstack");
        }
    }

    private void logMsg(String msg) {
        Log.d("FragmentExample.Main",msg);
    }
}