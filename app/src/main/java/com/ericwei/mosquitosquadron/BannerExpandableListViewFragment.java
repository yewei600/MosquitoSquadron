package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;

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
    private ExpandableListView expandableListView;
    private List<String> expandableListTitle;
    private HashMap<String, String> expandableListDetail;
    private String bannerDate = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getActivity().setTitle("Nov 18th Banner");

       // new RestOperation().execute(getString(R.string.backend_url));
        new RestOperation().execute("http://frozen-savannah-70920.herokuapp.com/contacts/");

        expandableListDetail = new HashMap<String, String>();
        expandableListTitle = new ArrayList<String>(expandableListDetail.keySet());

        listAdapter = new BannerExpandableListAdapter(getActivity(), expandableListTitle, expandableListDetail);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.banner_expandable_listview, container, false);
        expandableListView = (ExpandableListView) view.findViewById(R.id.expandableListView);
        View emptyView = view.findViewById(R.id.expandableListView_empty);
        expandableListView.setEmptyView(emptyView);
        expandableListView.setAdapter(listAdapter);

        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public class RestOperation extends AsyncTask<String, Void, Void> {
        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            // super.onPreExecute();
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
                updateEmptyView();
            } catch (JSONException e) {
                System.out.println("End of content");
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void aVoid) {
            getActivity().setTitle(bannerDate);

            listAdapter.notifyDataSetChanged();
            dialog.hide();
        }
    }

    private void updateEmptyView() {
        if (listAdapter.getGroupCount() == 0) {
            TextView tv = (TextView) getView().findViewById(R.id.expandableListView_empty);
            if (null != tv) {
                // if cursor is empty, why? do we have an invalid location
                int message = R.string.empty_banner_list;
                if (!Utility.isNetworkAvailable(getActivity())) {
                    message = R.string.empty_banner_list_no_network;
                }
                tv.setText(message);
            }
        }
    }

}
