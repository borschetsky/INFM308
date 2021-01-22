package com.example.infm308;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import  androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ViewPager viewPager;
    private ToDo toDoFragment;
    private DoneFragment doneFragment;
    private TabLayout tabLayout;
    JSONObject requestResponse;

    public static String SWAPI = "https://swapi.dev/api/people/1/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        toDoFragment = new ToDo();
        doneFragment = new DoneFragment();

        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(toDoFragment, "Characters");
        viewPagerAdapter.addFragment(doneFragment, "Starships");

        viewPager.setAdapter(viewPagerAdapter);


        Toast.makeText(MainActivity.this, "response.toString()", Toast.LENGTH_LONG).show();
        Thread thread = new Thread(new Runnable(){
            @Override
            public void run(){
                //code to do the HTTP request
                Request();
            }
        });
        thread.start();
    }

    public  void Request (){
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);

        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                SWAPI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(MainActivity.this, "response.toString()", Toast.LENGTH_LONG).show();
                        Snackbar.make(viewPager, "Hello from request,", Snackbar.LENGTH_LONG).show();
//                        toDoFragment.setData(response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity.this, "response.toString()", Toast.LENGTH_LONG).show();

                    }
                }
        );

        queue.add(objectRequest);
    }

    public  String getData(){
        return this.requestResponse.toString();
    }


}