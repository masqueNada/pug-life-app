package com.onurdursun.puglife;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity {
    private static String url = "https://dog.ceo/api/breed/pug/images";
    ProgressBar progressBar;
    String[] pugs;
    GridView pugGridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new GetPugs().execute();

        pugGridView = findViewById(R.id.pugGridView);
        progressBar = findViewById(R.id.progressBar);

    }

    private class GetPugs extends AsyncTask<Void, Void, String[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String[] doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            // Making a request to url and getting the response
            String jsonStr = sh.makeServiceCall(url);

            Log.e("Error", "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    // Get pug image urls and put them in an array of string
                    JSONArray pugUrlsJSON = jsonObj.getJSONArray("message");
                        String[] pugUrls = new String[pugUrlsJSON.length()];
                        for (int i = 0; i < pugUrlsJSON.length(); i++){
                            pugUrls[i] = pugUrlsJSON.getString(i);
                        }
                        return pugUrls;

                } catch (final JSONException e) {
                    Log.e("Error", "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e("Error", "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Check your connection!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(String[] pugUrls) {
            if(pugUrls == null) {
                // TODO: HANDLE THIS
            }
            progressBar.setVisibility(View.GONE);

            pugs = pugUrls;
            final GridAdapter pugsAdapter = new GridAdapter(getApplicationContext(), pugs);
            pugGridView.setAdapter(pugsAdapter);

            // Pug thumbnail click handler
            pugGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView parent, View view, int position, long id) {

                    Intent i = new Intent(getBaseContext(),PugActivity.class);
                    i.putExtra("pugUrls", pugs);
                    i.putExtra("pugPosition", position);
                    startActivity(i);

                    pugsAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);
    }
}


