package com.bidbuzz.androidApp.fragments;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bidbuzz.androidApp.R;

public class BidVBox extends Fragment {

    private BidVboxViewModel mViewModel;

    public static BidVBox newInstance() {
        return new BidVBox();
    }

    public static String mi;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        String str = getArguments().getString("ii");
        View view = inflater.inflate(R.layout.bid_v_box_fragment, container, false);
        TextView txt = view.findViewById(R.id.text_view);
        txt.setText(str);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mViewModel = ViewModelProviders.of(this).get(BidVboxViewModel.class);
        // TODO: Use the ViewModel
    }

}
