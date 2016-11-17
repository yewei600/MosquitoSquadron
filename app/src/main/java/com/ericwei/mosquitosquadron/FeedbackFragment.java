package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.suredigit.inappfeedback.FeedbackDialog;


/**
 * Created by eric on 13/11/16.
 */
public class FeedbackFragment extends Fragment {

    private FeedbackDialog feedBack;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        Button button = (Button) view.findViewById(R.id.comment_button);
        feedBack = new FeedbackDialog(getActivity(), "AF-ED19D3699F52-FE");

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                feedBack.show();
            }
        });

        return view;
    }

    @Override
    public void onPause() {
        super.onPause();
        feedBack.dismiss();
    }
}
