package com.example.practica;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.practica.Adapter.adapterPokemon;
import com.example.practica.model.classPokemon;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class pokemonListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon_list);

        RecyclerView recyclerView = findViewById(R.id.RecyclerView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

        Retrofit retrofit = new Retrofit.Builder()

                .baseUrl("https://upn.lumenes.tk/pokemons/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service service = retrofit.create(service.class);
        Call<List<classPokemon>> listGet = service.getListPokemones();

        listGet.enqueue(new Callback<List<classPokemon>>() {
            @Override
            public void onResponse(Call<List<classPokemon>> call, Response<List<classPokemon>> response) {

                String code = String.valueOf(response.code());
                if (code.equals("200")) {
                    Toast.makeText(getApplicationContext(), "HAY LISTA", Toast.LENGTH_SHORT).show();
                    List<classPokemon> myList = response.body();
                    adapterPokemon adapterList = new adapterPokemon(myList, pokemonListActivity.this);
                    recyclerView.setAdapter(adapterList);
                } else {
                    Toast.makeText(getApplicationContext(), "NO HAY LISTA", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<classPokemon>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_SHORT).show();
            }
        });
    }
}