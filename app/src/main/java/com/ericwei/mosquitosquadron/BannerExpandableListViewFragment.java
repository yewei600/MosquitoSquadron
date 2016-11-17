package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;

import com.ericwei.mosquitosquadron.models.BannerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by yucwei on 11/15/16.
 * http://www.journaldev.com/9942/android-expandablelistview-example-tutorial
 */

public class BannerExpandableListViewFragment extends Fragment {

    private BannerExpandableListAdapter listAdapter;
    private SwipeRefreshLayout swipeContainer;
    private ExpandableListView expandableListView;
    private List<String> expandableListTitle;
    private HashMap<String, String> expandableListDetail;
    private String bannerDate = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivity().setTitle("Nov 18th Banner");

        new RestOperation().execute(getString(R.string.backend_url));

        expandableListDetail = new HashMap<String, String>();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        listAdapter = new BannerExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.banner_expandable_listview, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListView.setAdapter(listAdapter);

        swipeContainer = (SwipeRefreshLayout) view.findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new RestOperation().execute(getString(R.string.backend_url));
                listAdapter = new BannerExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
                expandableListView.setAdapter(listAdapter);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);
        return view;
    }

    public class RestOperation extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {

            if(expandableListTitle.size()!=0){
                expandableListTitle.clear();
                expandableListDetail.clear();
            }

            dialog = new ProgressDialog(getContext());
            dialog.setMessage("Loading Banner...");
            dialog.setCancelable(false);
            dialog.setInverseBackgroundForced(false);
            dialog.show();
        }

        @Override
        protected Void doInBackground(String... params) {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(params[0]).build();

            try {
                Response response = client.newCall(request).execute();
                JSONArray array = new JSONArray(response.body().string());

                for (int i = 0; i < array.length(); i++) {
                    JSONObject object = array.getJSONObject(i);

                    BannerModel bannerItem = new BannerModel(object.getString("_id"),
                            object.getString("firstName"), object.getString("lastName"));

                    if (bannerItem.getFirstname().equals("date")) {
                        bannerDate = bannerItem.getLastname();
                    } else {
                        expandableListTitle.add(bannerItem.getFirstname());
                        expandableListDetail.put(bannerItem.getFirstname(), bannerItem.getLastname());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                System.out.println("End of content");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            getActivity().setTitle(bannerDate);
            swipeContainer.setRefreshing(false);

            listAdapter.notifyDataSetChanged();
            dialog.hide();
        }
    }

}
