package com.example.infm308;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;

import com.example.infm308.models.Vehicle;
import com.squareup.picasso.Picasso;

public class VehicleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vehicle);

        Vehicle vehicle = (Vehicle) getIntent().getSerializableExtra("vehicle");
        Toolbar toolbar = findViewById(R.id.vehicleToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(vehicle.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView view = findViewById(R.id.vehicle_Image);
        Picasso.get().load(vehicle.avatar).into(view);
    }
}