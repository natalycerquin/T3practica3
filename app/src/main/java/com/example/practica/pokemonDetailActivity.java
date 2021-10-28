package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.practica.model.classPokemon;
import com.squareup.picasso.Picasso;

public class pokemonDetailActivity extends AppCompatActivity {

    ImageView image_poke;
    EditText namePoke, typePoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_detail);

        image_poke = findViewById(R.id.image_poke);
        namePoke = findViewById(R.id.namePoke);
        typePoke = findViewById(R.id.typePoke);

        classPokemon pokemon = (classPokemon) getIntent().getSerializableExtra("poke");

        namePoke.setText(pokemon.getNombre());
        typePoke.setText(pokemon.getTipo());

        Picasso.get().load(pokemon.getUrl_imagen()).into(image_poke);

        String la = String.valueOf(pokemon.getLatitude());
        String lo = String.valueOf(pokemon.getLongitude());

        findViewById(R.id.location_poke).setOnClickListener(view -> {
            Intent intent = new Intent(this, MapsActivity.class);
            intent.putExtra("lati", la);
            intent.putExtra("long", lo);
            intent.putExtra("name", pokemon.getNombre());
            startActivity(intent);
        });
    }
}