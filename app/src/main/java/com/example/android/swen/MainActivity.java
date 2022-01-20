package com.example.android.swen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MainActivity  extends AppCompatActivity  {

    ArrayList<NewsItems> newsItems = new ArrayList<NewsItems>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final String URL = "https://saurav.tech/NewsAPI/top-headlines/category/health/in.json";//"https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=6a93516b20bd46b7895ec96cb763a469";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null
                , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                JSONArray newsArray = null;
                try {
                    newsArray = response.getJSONArray("articles");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < newsArray.length(); i++) {
                    String urlToImage="";
                    String newsSource = "";
                    String newsDescription = "";
                    String urlToWeb = "";
                    try {

                        newsSource = newsArray.getJSONObject(i).getJSONObject("source").getString("name");
                        urlToImage = newsArray.getJSONObject(i).getString("urlToImage");
                        newsDescription = newsArray.getJSONObject(i).getString("description");
                        urlToWeb = newsArray.getJSONObject(i).getString("url");
                        //Toast.makeText(MainActivity.this, newsItems.get(i).mNewsDescription+"\n\n"+newsItems.get(i).mNewsSource, Toast.LENGTH_SHORT).show();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    newsItems.add(new NewsItems(urlToImage, newsDescription, newsSource,urlToWeb));

                }
                RecyclerView recyclerView = findViewById(R.id.newsRecyleView);
                NewsAdapter adapter = new NewsAdapter(newsItems);
                recyclerView.setAdapter(adapter);

                //Toast.makeText(MainActivity.this, String.valueOf(newsItems.size()), Toast.LENGTH_SHORT).show();
            }
        }
                ,new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "That didn't work!", Toast.LENGTH_SHORT).show();
            }
        });

        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }
}