package com.ericwei.mosquitosquadron;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MainFragment extends Fragment {

    private TextView tvData;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        tvData = (TextView) view.findViewById(R.id.tv1);
        Button getData = (Button) view.findViewById(R.id.getData);

        getData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new RestOperation().execute("http://frozen-savannah-70920.herokuapp.com/contacts");
            }
        });

        return view;
    }


    public class RestOperation extends AsyncTask<String, String, String> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //dialog.show();
        }

        @Override
        protected String doInBackground(String... params) {
            HttpURLConnection connection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL(params[0]);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));
                StringBuffer buffer = new StringBuffer();
                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                return buffer.toString();

//            String finalJson = buffer.toString();
//
//            JSONObject parentObject = new JSONObject(finalJson);
//            JSONArray parentArray = parentObject.getJSONArray("movies");
//
//            List<BannerModel> BannerModelList = new ArrayList<>();
//
//            Gson gson = new Gson();
//            for(int i=0; i<parentArray.length(); i++) {
//                JSONObject finalObject = parentArray.getJSONObject(i);
//                /**
//                 * below single line of code from Gson saves you from writing the json parsing yourself which is commented below
//                 */
//                BannerModel bannerModel = gson.fromJson(finalObject.toString(), BannerModel.class);
////                    movieModel.setMovie(finalObject.getString("movie"));
////                    movieModel.setYear(finalObject.getInt("year"));
////                    movieModel.setRating((float) finalObject.getDouble("rating"));
////                    movieModel.setDirector(finalObject.getString("director"));
////
////                    movieModel.setDuration(finalObject.getString("duration"));
////                    movieModel.setTagline(finalObject.getString("tagline"));
////                    movieModel.setImage(finalObject.getString("image"));
////                    movieModel.setStory(finalObject.getString("story"));
////
////                    List<MovieModel.Cast> castList = new ArrayList<>();
////                    for(int j=0; j<finalObject.getJSONArray("cast").length(); j++){
////                        MovieModel.Cast cast = new MovieModel.Cast();
////                        cast.setName(finalObject.getJSONArray("cast").getJSONObject(j).getString("name"));
////                        castList.add(cast);
////                    }
////                    movieModel.setCastList(castList);
//                // adding the final object in the list
//                movieModelList.add(movieModel);
//            }
//            return movieModelList;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (connection != null) {
                    connection.disconnect();
                }
                try {
                    if (reader != null) {
                        reader.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            tvData.setText(result);
//        dialog.dismiss();
//        if (result != null) {
//            MovieAdapter adapter = new MovieAdapter(getApplicationContext(), R.layout.row, result);
//            lvMovies.setAdapter(adapter);
//            lvMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//                @Override
//                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                    MovieModel movieModel = result.get(position);
//                    Intent intent = new Intent(MainActivity.this, DetailActivity.class);
//                    intent.putExtra("movieModel", new Gson().toJson(movieModel));
//                    startActivity(intent);
//                }
//            });
//        } else {
//            Toast.makeText(getApplicationContext(), "Not able to fetch data from server, please check url.", Toast.LENGTH_SHORT).show();
//        }
        }
    }
}

