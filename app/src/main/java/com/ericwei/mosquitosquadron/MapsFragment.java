package com.ericwei.mosquitosquadron;


import android.app.ListFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;


public class MapsFragment extends ListFragment {

    private ListView lv;
    private String[] items;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);

    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ListAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.mapItems, android.R.layout.simple_expandable_list_item_1);
        setListAdapter(adapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        String locName = l.getItemAtPosition(position).toString();
        MapDetailsFragment detailsFragment = MapDetailsFragment.newInstance(locName);
//        Bundle bundle = new Bundle();
//
//        bundle.putString("LOC_NAME", locName);
//        detailsFragment.setArguments(bundle);

        this.getFragmentManager().beginTransaction()
                .replace(R.id.content_frame, detailsFragment, null)
                .addToBackStack(null).commit();

        // Toast.makeText(getActivity(), l.getItemAtPosition(position).toString(), Toast.LENGTH_LONG).show();
    }

}
