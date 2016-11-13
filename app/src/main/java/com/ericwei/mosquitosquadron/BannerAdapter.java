package com.ericwei.mosquitosquadron;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ericwei.mosquitosquadron.models.BannerModel;

import java.util.List;

/**
 * Created by eric on 30/10/16.
 */
public class BannerAdapter extends RecyclerView.Adapter<BannerAdapter.ViewHolder> {

    private Context context;
    private List<BannerModel> bannerItems;

    public BannerAdapter(Context context, List<BannerModel> bannerItems) {
        this.context = context;
        this.bannerItems = bannerItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.banner_card, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //HAVE TO FIX THIS STUFF LATER:  the names of the fields and their view counterparts
        holder.description.setText(bannerItems.get(position).getFirstname());
        holder.content.setText(bannerItems.get(position).getLastname());
        //Glide.with(context).load(bannerItems.get(position).getIm)
    }

    @Override
    public int getItemCount() {
        return bannerItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView description;
        public TextView content;
        //public ImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            //imageView = (ImageView) itemView.findViewById(R.id.card_image);
            content = (TextView) itemView.findViewById(R.id.content);
        }

    }
}
