package com.ericwei.mosquitosquadron;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

/**
 * Created by eric on 13/11/16.
 */

public class SquadronActivityDialog extends DialogFragment {
    private String message;
    private TextView activityDetail;

    public SquadronActivityDialog() {

    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.aboutsquadron_dialog, null);

        message = getArguments().getString("message");
        activityDetail = (TextView) view.findViewById(R.id.activity_detail);
        activityDetail.setMovementMethod(new ScrollingMovementMethod());
        activityDetail.setText(message);

        builder.setView(view);

        Dialog dialog = builder.create();
        return dialog;
    }


}
