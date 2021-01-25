package com.example.infm308.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.infm308.MainActivity;
import com.example.infm308.R;
import com.example.infm308.adapters.FavoritesAdapter;
import com.example.infm308.db.SwapiDbHelper;
import com.example.infm308.models.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FavoritesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FavoritesFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private SwapiDbHelper swapiDbHelper;
    public FavoritesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FavoritesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FavoritesFragment newInstance(String param1, String param2) {
        FavoritesFragment fragment = new FavoritesFragment();
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
        swapiDbHelper = new SwapiDbHelper(ctx);

        View rootView = inflater.inflate(R.layout.fragment_favorites, container, false);
        ListView listView = rootView.findViewById(R.id.favorites_listView);

        ArrayList<Person> personslist = swapiDbHelper.getAllFavorites();

        if(personslist.size() == 0 || personslist == null){
            TextView view = rootView.findViewById(R.id.favoritesFragment);
            view.setText("You still hav no items.");
            return  rootView;
        }
        FavoritesAdapter adapter = new FavoritesAdapter(activity.getApplicationContext(), personslist);
        listView.setAdapter(adapter);

        return rootView;
    }
}