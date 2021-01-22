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
import com.example.infm308.models.Vehicle;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class VihiclesAdapter extends ArrayAdapter<Vehicle> {

    public VihiclesAdapter(@NonNull Context context, @NonNull ArrayList<Vehicle> objects) {
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


        Vehicle currentVehicle = getItem(position);


        TextView magnitudeView = listItemView.findViewById(R.id.main_title);

        magnitudeView.setText(currentVehicle.name);
        TextView manufarturesView = listItemView.findViewById(R.id.sub_title);

        manufarturesView.setText(currentVehicle.manufacturer);
        ImageView avatarView = listItemView.findViewById(R.id.image);
        Picasso.get().load(currentVehicle.avatar).into(avatarView);

        return listItemView;
    }
}
