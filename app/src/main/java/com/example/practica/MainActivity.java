package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.my_poke).setOnClickListener(view -> startActivity(new Intent(this, pokemonListActivity.class)));
        findViewById(R.id.register_poke).setOnClickListener(view -> startActivity(new Intent(this, registerPokemonActivity.class)));
    }
}