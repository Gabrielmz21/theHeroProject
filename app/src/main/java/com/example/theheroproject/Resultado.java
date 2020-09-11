package com.example.theheroproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class Resultado extends AppCompatActivity {
    String nombreHeroe;
    String TOKEN = "3779860228695675";
    final HashMap<String,String> id_name = new HashMap<String, String>();
    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);

        Intent intent = getIntent();
        nombreHeroe = intent.getStringExtra("NOMBRE_HEROE");

        final TextView textView = findViewById(R.id.textView4);
        final ListView listView = findViewById(R.id.listview);

        mQueue = Volley.newRequestQueue(this);
        String url = "https://www.superheroapi.com/api.php/"+TOKEN+"/search/" + nombreHeroe;
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            textView.setText("Resultados para "+nombreHeroe+": "+String.valueOf(jsonArray.length()));
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject info = jsonArray.getJSONObject(i);
                                System.out.println(info.getString("id")+" "+info.getString("name"));
                                id_name.put(info.getString("id"),info.getString("name"));
                                ArrayList<String> myStringArray = new ArrayList<String>();
                                myStringArray.add(info.getString("name"));
                                
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mQueue.add(request);
    }






}