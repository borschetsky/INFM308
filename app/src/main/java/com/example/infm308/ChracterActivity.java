package com.example.infm308;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;

import com.example.infm308.models.Person;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

public class ChracterActivity extends AppCompatActivity {

    TextView charName;
    TextView charGender;
    TextView charBirth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chracter);
        Person person = (Person) getIntent().getSerializableExtra("person");

        Toolbar toolbar = findViewById(R.id.chracterToolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setTitle(person.name);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        toolbar.setTitle(person.name);

        ImageView view = findViewById(R.id.char_Image);
        Picasso.get().load(person.avatar).into(view);
        //Name
        charName = findViewById(R.id.char_Name);
        charName.setText(person.name);
        //Gender
        charGender = findViewById(R.id.char_Gender);
        charGender.setText(person.gender);
        //Birth
        charBirth = findViewById(R.id.char_Birth);
        charBirth.setText(person.birth_year);

//        Snackbar.make(view, person.name, Snackbar.LENGTH_LONG).show();

    }
}