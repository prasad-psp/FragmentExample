package com.psp.fragmentexample.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.psp.fragmentexample.R;

public class FragmentTwo extends Fragment {

    @Override
    public void onResume() {
        super.onResume();
        logMsg("onResume");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        logMsg("onDestroy");
    }

    @Override
    public void onPause() {
        super.onPause();
        logMsg("onPause");
    }

    @Override
    public void onStop() {
        super.onStop();
        logMsg("onStop");
    }

    @Override
    public void onStart() {
        super.onStart();
        logMsg("onStart");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        logMsg("onViewCreated");
    }

    private void logMsg(String msg) {
        Log.d("FragmentExample.Two",msg);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        logMsg("onCreateView");
        return inflater.inflate(R.layout.fragment_two, container, false);
    }
}