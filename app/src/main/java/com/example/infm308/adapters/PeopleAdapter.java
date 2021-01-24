package com.example.infm308.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.infm308.R;
import com.example.infm308.models.Person;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PeopleAdapter extends ArrayAdapter<Person> {
    public PeopleAdapter(@NonNull Context context,  @NonNull ArrayList<Person> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.row, parent, false);
        }


        Person currentPerson = getItem(position);


        TextView magnitudeView = listItemView.findViewById(R.id.main_title);
        // Display the magnitude of the current earthquake in that TextView
        magnitudeView.setText(currentPerson.name);
        TextView subTitleView = listItemView.findViewById(R.id.sub_title);
        // Display the magnitude of the current earthquake in that TextView
        subTitleView.setText(currentPerson.gender);
        ImageView avatarView = listItemView.findViewById(R.id.image);
        Picasso.get().load(currentPerson.avatar).into(avatarView);
//        new DownloadImageTask(avatarView).execute(currentPerson.avatar);
        // Find the TextView with view ID location


        // Return the list item view that is now showing the appropriate data
        return listItemView;
    }


}
