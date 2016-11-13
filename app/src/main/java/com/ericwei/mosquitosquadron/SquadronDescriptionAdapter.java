package com.ericwei.mosquitosquadron;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Arrays;
import java.util.List;

/**
 * Created by eric on 06/11/16.
 */

public class SquadronDescriptionAdapter extends RecyclerView.Adapter<SquadronDescriptionAdapter.ViewHolder> {

    private Context context;
    private List<String> activityItems;

    public SquadronDescriptionAdapter(Context context) {
        this.context = context;
        activityItems = Arrays.asList(context.getResources().getStringArray(R.array.squadron_activities_array));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutsquadron_parent, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int mExpandedPosition = -1;
        final boolean isExpanded = position == mExpandedPosition;
       // holder.deta


        holder.activity_description.setText(activityItems.get(position));
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView activity_description;
        public ImageView activity_image;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_description = (TextView) itemView.findViewById(R.id.activity_description);
            activity_image = (ImageView) itemView.findViewById(R.id.card_image);
        }
    }
}
