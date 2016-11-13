package com.ericwei.mosquitosquadron;


import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ericwei.mosquitosquadron.models.SquadronActivity;

import java.util.List;

/**
 * NOT FUCKING WORKING ................
 *
 * FUCK
 */

public class SquadronDescriptionAdapter extends RecyclerView.Adapter<SquadronDescriptionAdapter.ViewHolder> {

    private Context context;
    private final List<SquadronActivity> activityItems;
    private final OnItemClickListener listener;

    public SquadronDescriptionAdapter(List<SquadronActivity> activityItems,
                                      OnItemClickListener listener) {
        this.activityItems = activityItems;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.aboutsquadron_card, parent, false);
        return new ViewHolder(itemView, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.activity_description.setText(activityItems.get(position).getActivityName());
        holder.activity_image.setImageResource(activityItems.get(position).getImageResourceId());
        holder.setOnItemClickListener(listener);
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

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);
            activity_description = (TextView) itemView.findViewById(R.id.activity_title);
            activity_image = (ImageView) itemView.findViewById(R.id.activity_image);
            cardView = (CardView) itemView.findViewById(R.id.activity_cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        listener.onItemClick(activity_description.getText().toString());
                    }
                }
            });
        }


        public void setOnItemClickListener(OnItemClickListener listener) {
            this.listener = listener;
        }

        public void showDialog(View view) {
            SquadronActivityDialog dialog = new SquadronActivityDialog();
            //dialog.show(getFragmentManager(), "yeeeeeeeeeeeeeeeehaaaaa");
        }
    }

}
