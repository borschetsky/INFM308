package com.example.infm308;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.infm308.db.SwapiDbHelper;
import com.example.infm308.models.Person;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ChracterActivity extends AppCompatActivity {
    private SwapiDbHelper swapiDbHelper;

    String intentKey = "person";
    TextView charName;
    TextView charGender;
    TextView charBirth;
    Button insertBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        swapiDbHelper = new SwapiDbHelper(this);

        setContentView(R.layout.activity_chracter);
        Person person = (Person) getIntent().getSerializableExtra(intentKey);

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

        insertBtn = findViewById(R.id.insert_char_btn);
        boolean isAdded = swapiDbHelper.isAdded(person.name);
//        this.deleteDatabase("swapi.db");
        if(isAdded){
            insertBtn.setEnabled(false);
            insertBtn.setText(R.string.btn_added);
        } else{
            insertBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    swapiDbHelper.addPersonToFavorites(person);

                    Snackbar.make(insertBtn, R.string.added_to_f, Snackbar.LENGTH_LONG).show();

                    insertBtn.setEnabled(false);
                    insertBtn.setText(R.string.btn_added);
                }
            });
        }
    }
}