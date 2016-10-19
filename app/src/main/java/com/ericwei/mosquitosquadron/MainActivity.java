package com.ericwei.mosquitosquadron;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private TextView tvData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // tvData = (TextView) findViewById(R.id.tv1);

//        Button getData = (Button) findViewById(R.id.getData);
//        getData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                //String restURL = "http://frozen-savannah-70920.herokuapp.com/contacts";
//                new RestOperation().execute("http://frozen-savannah-70920.herokuapp.com/contacts");
//            }
//        });
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.app.FragmentManager fm = getFragmentManager();
        fm.beginTransaction().replace(R.id.content_frame, new MainFragment()).commit();

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
//
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


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        android.app.FragmentManager fm = getFragmentManager();


        int id = item.getItemId();
        Fragment fragment = null;
        Class fragmentClass = null;
        if (id == R.id.nav_about) {
            fm.beginTransaction().replace(R.id.content_frame, new AboutSquadronFragment()).commit();
        } else if (id == R.id.nav_calendar) {
            Intent i = new Intent(MainActivity.this, MapActivity.class);
            startActivity(i);
        } else if (id == R.id.nav_map) {


        } else if (id == R.id.nav_contact) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

        return true;
    }


}
