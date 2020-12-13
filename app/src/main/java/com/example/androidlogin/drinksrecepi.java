package com.example.androidlogin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.util.ArrayList;

public class drinksrecepi extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    //private ImageAdapter mAdapter;
    private ArrayList<CoffeeItem> coffeeItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drinksrecepi);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(new CoffeeAdapter(coffeeItems,this));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        coffeeItems.add(new CoffeeItem(R.raw.mint, "Mint tea","0","0"));
        coffeeItems.add(new CoffeeItem(R.raw.lemonade, "Lemonade","1","0"));
        coffeeItems.add(new CoffeeItem(R.raw.coconutmilkshake, "Coconut shake","2","0"));

    }
}