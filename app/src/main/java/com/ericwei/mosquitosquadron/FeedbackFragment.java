package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.suredigit.inappfeedback.FeedbackDialog;


/**
 * Created by eric on 13/11/16.
 */
public class FeedbackFragment extends Fragment {

    private FeedbackDialog feedBack;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        feedBack = new FeedbackDialog(getActivity(), "AF-ED19D3699F52-FE");

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        feedBack.show();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onPause() {
        super.onPause();
        feedBack.dismiss();
    }
}
