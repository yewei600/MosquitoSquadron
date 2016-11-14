package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ericwei.mosquitosquadron.models.SquadronActivity;

import java.util.ArrayList;
import java.util.List;

//In this fragment, descriptions are provided for the activities and training offered by the squadron

public class AboutSquadronFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private SquadronDescriptionAdapter adapter;
    private List<SquadronActivity> activity_list;

    private String activityList[] = {"friday_night_training", "aviation", "sports",
            "band", "drill", "trips"};

    private int activityImages[] = {R.drawable.friday_night, R.drawable.aviation, R.drawable.sports,
            R.drawable.band, R.drawable.drill, R.drawable.trips};


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        activity_list = new ArrayList<>();

        initializeList();
        getActivity().setTitle("Squadron Activities");
    }

    public void initializeList() {
        activity_list.clear();

        for (int i = 0; i < activityList.length; i++) {
            SquadronActivity activity = new SquadronActivity();
            activity.setActivityName(activityList[i]);
            activity.setImageResourceId(activityImages[i]);
            activity_list.add(activity);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_squadron, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_aboutSquadron);
        recyclerView.setLayoutManager(gridLayoutManager);

        if (activity_list.size() > 0 && recyclerView != null) {
            adapter = new SquadronDescriptionAdapter(activity_list, getActivity());
            recyclerView.setAdapter(adapter);
        }

        return view;
    }


}
