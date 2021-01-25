package com.example.infm308;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.example.infm308.adapters.ViewPagerAdapter;
import com.example.infm308.fragments.FavoritesFragment;
import com.example.infm308.fragments.CharactersFragment;
import com.example.infm308.fragments.StarshipsFragment;
import com.example.infm308.service.MusicThemeService;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private final String CHARACTERS = "Characters";
    private final String STARSHIPS = "Starships";
    private final String FAVORITES = "Favorites";

    private Toolbar toolbar;
    private ViewPager viewPager;
    private CharactersFragment toDoFragment;
    private StarshipsFragment doneFragment;
    private FavoritesFragment favoritesFragment;

    private TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);

        toDoFragment = new CharactersFragment();
        doneFragment = new StarshipsFragment();
        favoritesFragment = new FavoritesFragment();
        tabLayout.setupWithViewPager(viewPager);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);
        viewPagerAdapter.addFragment(toDoFragment, CHARACTERS);
        viewPagerAdapter.addFragment(doneFragment, STARSHIPS);
        viewPagerAdapter.addFragment(favoritesFragment, FAVORITES);

        viewPager.setAdapter(viewPagerAdapter);

        startService(new Intent(this, MusicThemeService.class));
    }

    @Override
    protected void onDestroy() {
//        stopService(new Intent(this, MusicThemeService.class));
        super.onDestroy();
    }
}