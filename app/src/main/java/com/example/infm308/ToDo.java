package com.example.infm308;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.infm308.adapters.PeopleAdapter;
import com.example.infm308.models.Person;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;

import android.content.Context;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ToDo#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ToDo extends Fragment {
    public static String SWAPI = "https://swapi.dev/api/people/";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    TextView textView;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ToDo() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ToDo.
     */
    // TODO: Rename and change types and number of parameters
    public static ToDo newInstance(String param1, String param2) {
        ToDo fragment = new ToDo();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MainActivity activity = (MainActivity)getActivity();
        Context ctx = activity.getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_to_do, container, false);

        ListView listView = rootView.findViewById(R.id.people_listView);

        Request(activity, listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Person obj =  (Person) listView.getAdapter().getItem(position);
//                String name = (String)obj.get("name");
//                Toast.makeText(activity.getApplicationContext(), "YOYOYO", Toast.LENGTH_LONG).show();
//                Snackbar.make(listView, obj.name, Snackbar.LENGTH_LONG).show();
                Intent intent = null;
                if(intent == null){
                    intent = new Intent(activity.getApplicationContext(), ChracterActivity.class);
                }
                intent.putExtra("person", obj);
                startActivity(intent);
            }
        });
        return rootView;
    }

    public  void setData (String data) {
            this.textView.setText(data);
    }

    public  void Request (MainActivity activity, ListView listView){
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                SWAPI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray array = null;
                        Gson gson = new Gson();

                        ArrayList<Person> personArray = new ArrayList<>();
                        try {
                            array = response.getJSONArray("results");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        if(array != null){
                            for (int i = 0; i< array.length(); i++){
                                String obj;
                                try {
                                     obj =  array.getJSONObject(i).toString();
                                     Person p = gson.fromJson(obj, Person.class);
                                     String avatarUrl = "https://starwars-visualguide.com/assets/img/characters/" + (i + 1) +".jpg";
                                     p.setAvatar(avatarUrl);
                                     personArray.add(p);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }
                        String test = response.toString();
                        PeopleAdapter adapter = new PeopleAdapter(activity.getApplicationContext(), personArray);
                        listView.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                    }
                }
        );
        MySingleton.getInstance(activity.getApplicationContext()).addToRequestQueue(objectRequest);
    }
}