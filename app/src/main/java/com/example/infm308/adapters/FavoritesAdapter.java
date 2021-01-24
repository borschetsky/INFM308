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

import com.example.infm308.R;
import com.example.infm308.entities.PersonEntity;
import com.example.infm308.fragments.FavoritesFragment;
import com.example.infm308.models.Person;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FavoritesAdapter extends ArrayAdapter<PersonEntity> {
    ArrayList<PersonEntity> objects;
    public FavoritesAdapter(@NonNull Context context,  @NonNull ArrayList<PersonEntity> objects) {
        super(context, 0, objects);
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
        PersonEntity entity = getItem(position);
        if(entity != null){
            TextView name = listItemView.findViewById(R.id.main_title_sql);
            name.setText(entity.getName());

            TextView snubTitle = listItemView.findViewById(R.id.sub_title_sql);
            snubTitle.setText(entity.getGender());

            ImageView avatarView = listItemView.findViewById(R.id.image_sql);
            Picasso.get().load(entity.getAvatar()).into(avatarView);

            Button btn = listItemView.findViewById(R.id.button_sql);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Snackbar.make(v, entity.getName() + " removed from favorites.", Snackbar.LENGTH_LONG).show();
                    entity.delete();
                    remove(entity);

                }
            });
        }


        return listItemView;
    }

    }
