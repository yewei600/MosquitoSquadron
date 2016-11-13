package com.ericwei.mosquitosquadron;

import android.media.Image;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;

/**
 * Created by eric on 12/11/16.
 */

public class AboutSquadronParentViewHolder extends ParentViewHolder {

    public ImageView mActivityImageView;
    public TextView mActivityDescription;

    /**
     * Default constructor.
     *
     * @param itemView The {@link View} being hosted in this ViewHolder
     */
    public AboutSquadronParentViewHolder(@NonNull View itemView) {
        super(itemView);

        mActivityImageView = (ImageView) itemView.findViewById(R.id.card_image);
        mActivityDescription = (TextView) itemView.findViewById(R.id.activity_description);
    }
}
