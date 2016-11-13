package com.ericwei.mosquitosquadron;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;

/**
 * Created by eric on 12/11/16.
 */

public class AboutSquadronChildViewHolder extends ChildViewHolder {
    public TextView mFullActivityDescription;


    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public AboutSquadronChildViewHolder(@NonNull View itemView) {
        super(itemView);

        mFullActivityDescription = (TextView) itemView.findViewById(R.id.full_activity_description);
    }
}
