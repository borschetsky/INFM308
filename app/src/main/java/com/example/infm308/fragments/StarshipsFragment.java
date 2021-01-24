package com.example.infm308.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.infm308.MainActivity;
import com.example.infm308.utility.MySingleton;
import com.example.infm308.R;
import com.example.infm308.VehicleActivity;
import com.example.infm308.adapters.VihiclesAdapter;
import com.example.infm308.models.Vehicle;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StarshipsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarshipsFragment extends Fragment {
    public static String SWAPI = "https://swapi.dev/api/vehicles/";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StarshipsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DoneFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarshipsFragment newInstance(String param1, String param2) {
        StarshipsFragment fragment = new StarshipsFragment();
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
        MainActivity activity = (MainActivity)getActivity();
        Context ctx = activity.getApplicationContext();
        View rootView = inflater.inflate(R.layout.fragment_done, container, false);
        ListView listView = rootView.findViewById(R.id.vehicles_listView);
        // Inflate the layout for this fragment
        Request(activity, listView);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Vehicle obj =  (Vehicle) listView.getAdapter().getItem(position);

                Intent intent = null;
                if(intent == null){
                    intent = new Intent(activity.getApplicationContext(), VehicleActivity.class);
                    intent.putExtra("vehicle", obj);
                    startActivity(intent);
                }

            }
        });
        return rootView;
    }

    public  void Request (MainActivity activity, ListView listView){
        JsonObjectRequest objectRequest = new JsonObjectRequest(
                Request.Method.GET,
                SWAPI,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
//                        textView.setText(response.toString());
                        JSONArray array = null;
                        Gson gson = new Gson();

                        ArrayList<Vehicle> vehiclesArray = new ArrayList<>();
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
                                    Vehicle p = gson.fromJson(obj, Vehicle.class);
                                    String avatarUrl = "https://starwars-visualguide.com/assets/img/vehicles/" + (i + 4) +".jpg";
                                    p.setAvatar(avatarUrl);
                                    vehiclesArray.add(p);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        }

                        String test = response.toString();
                        VihiclesAdapter adapter = new VihiclesAdapter(activity.getApplicationContext(), vehiclesArray);
                        listView.setAdapter(adapter);
//
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