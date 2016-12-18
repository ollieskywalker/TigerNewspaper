package com.example.oliverchang.tigernewspaper4.Presenter;

/**
 * Created by Oliver Chang on 12/17/2016.
 */

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.oliverchang.tigernewspaper4.R;

public class SportsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.sports_fragment, container, false);
    }
}