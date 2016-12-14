package com.example.logeshwa.knitv1.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.logeshwa.knitv1.R;

/**
 * Created by logeshwa on 11/22/2016.
 */

public class homefragment extends Fragment{

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.homescreen, container, false);

    }

}
