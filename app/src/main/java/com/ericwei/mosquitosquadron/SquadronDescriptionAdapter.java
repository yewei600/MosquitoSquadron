package com.ericwei.mosquitosquadron;


import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ericwei.mosquitosquadron.models.SquadronActivity;

import java.util.List;


public class SquadronDescriptionAdapter extends RecyclerView.Adapter<SquadronDescriptionAdapter.ViewHolder> {

    private Context context;
    private final List<SquadronActivity> activityItems;
    private final String[] activityDetailDescriptions;


    public SquadronDescriptionAdapter(List<SquadronActivity> activityItems, Context context) {
        this.activityItems = activityItems;
        this.context = context;
        this.activityDetailDescriptions = context.getResources()
                .getStringArray(R.array.squadron_activities_array);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutsquadron_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.activity_description.setText(activityItems.get(position).getActivityName());
        holder.activity_image.setImageResource(activityItems.get(position).getImageResourceId());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SquadronActivityDialog dialog = new SquadronActivityDialog(activityDetailDescriptions[position]);
                dialog.show(((Activity) context).getFragmentManager(), "tag");
            }
        });
    }

    @Override
    public int getItemCount() {
        return activityItems.size();
    }

    public interface OnItemClickListener {
        void onItemClick(String tvText);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView cardView;
        public TextView activity_description;
        public ImageView activity_image;
        private OnItemClickListener listener;

        public ViewHolder(View itemView) {
            super(itemView);
            activity_description = (TextView) itemView.findViewById(R.id.activity_title);
            activity_image = (ImageView) itemView.findViewById(R.id.activity_image);
            cardView = (CardView) itemView.findViewById(R.id.activity_cardView);
        }


        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }


    }

}
