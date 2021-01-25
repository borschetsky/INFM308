package com.example.infm308.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.infm308.MainActivity;
import com.example.infm308.R;
import com.example.infm308.db.SwapiDbHelper;
import com.example.infm308.fragments.FavoritesFragment;
import com.example.infm308.models.Person;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesAdapter extends ArrayAdapter<Person> {
    private SwapiDbHelper swapiDbHelper;
    ArrayList<Person> objects;
    Context context;
    public FavoritesAdapter(@NonNull Context context,  @NonNull ArrayList<Person> objects) {
        super(context, 0, objects);
        this.context = context;
        this.objects = objects;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.sql_row, parent, false);
        }
        Person entity = getItem(position);
        if(entity != null){
            TextView name = listItemView.findViewById(R.id.main_title_sql);
            name.setText(entity.name);

            TextView snubTitle = listItemView.findViewById(R.id.sub_title_sql);
            snubTitle.setText(entity.gender);

            ImageView avatarView = listItemView.findViewById(R.id.image_sql);
            Picasso.get().load(entity.avatar).into(avatarView);

            Button btn = listItemView.findViewById(R.id.button_sql);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, entity.name + " removed from favorites.", Snackbar.LENGTH_LONG).show();
                    swapiDbHelper = new SwapiDbHelper(context);
                    boolean isDeleted = swapiDbHelper.removeFromFavorites(entity.id);
                    if(isDeleted){
                        remove(entity);
                    }

                }
            });
        }


        return listItemView;
    }

    }
