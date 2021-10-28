package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practica.model.classPokemon;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class registerPokemonActivity extends AppCompatActivity {

    EditText namePoke, typePoke, latitudePoke, longitudePoke, urlPoke;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_pokemon);

        namePoke = findViewById(R.id.namePoke);
        typePoke = findViewById(R.id.typePoke);
        latitudePoke = findViewById(R.id.latitudePoke);
        longitudePoke = findViewById(R.id.longitudePoke);
        urlPoke = findViewById(R.id.urlPoke);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://upn.lumenes.tk/pokemons/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service service = retrofit.create(service.class);


        findViewById(R.id.registrarPoke).setOnClickListener(view -> {

            String nombre = namePoke.getEditableText().toString().trim();
            String tipo = typePoke.getEditableText().toString().trim();
            String lat = latitudePoke.getEditableText().toString().trim();
            String lon = longitudePoke.getEditableText().toString().trim();
            String img = urlPoke.getEditableText().toString().trim();

            classPokemon pok = new classPokemon(nombre, tipo, img, Float.parseFloat(lat), Float.parseFloat(lon));

            Call<Void> entre = service.postCreatePokemon(pok);
            entre.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    String respuesta = String.valueOf(response.code());
                    if (respuesta.equals("200")) {
                        Toast.makeText(getApplicationContext(), "Pokemon Registrado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), " Pokemon no Registrado", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        });


    }
}