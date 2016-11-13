package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

//In this fragment, descriptions are provided for the activities and training offered by the squadron

public class AboutSquadronFragment extends Fragment {

    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private SquadronDescriptionAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        gridLayoutManager = new GridLayoutManager(getActivity(), 1);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_squadron, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_aboutSquadron);
        recyclerView.setLayoutManager(gridLayoutManager);

        adapter = new SquadronDescriptionAdapter(getActivity());
        recyclerView.setAdapter(adapter);


        return view;
    }


}
