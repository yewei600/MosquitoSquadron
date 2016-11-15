package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ericwei.mosquitosquadron.models.BannerModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MainFragment extends Fragment {

    private TextView tvData;
    private RecyclerView recyclerView;
    private GridLayoutManager gridLayoutManager;
    private BannerAdapter adapter;
    private List<BannerModel> banner_list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getActivity().setTitle("180 Weekly Banner");
        //the second argument is how many cards to display in one row
        gridLayoutManager = new GridLayoutManager(getActivity(), 1);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view_banner);
        recyclerView.setLayoutManager(gridLayoutManager);
        banner_list = new ArrayList<>();
        new RestOperation().execute("http://frozen-savannah-70920.herokuapp.com/contacts");

        adapter = new BannerAdapter(getActivity(), banner_list);
        recyclerView.setAdapter(adapter);

        //to implement scrolling behaviour
//        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                if (gridLayoutManager.findLastCompletelyVisibleItemPosition() == banner_list.size() - 1) {
//
//                }
//
//            }
//        });

        return view;
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
                            object.getString("firstName"), object.getString("lastName"),
                            object.getString("createDate"));

                    banner_list.add(bannerItem);
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
            //     Toast.makeText(getActivity(), banner_list.get(0).getFirstname(), Toast.LENGTH_SHORT).show();
            //   Toast.makeText(getActivity(), banner_list.get(0).getLastname(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), banner_list.get(0).getId(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), banner_list.get(0).getCreateDate(), Toast.LENGTH_SHORT).show();
//            Toast.makeText(getActivity(), banner_list.get(0).getEmail(), Toast.LENGTH_SHORT).show();

            adapter.notifyDataSetChanged();
            dialog.hide();
        }
    }
}

