package com.example.practica.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practica.R;
import com.example.practica.model.classPokemon;
import com.example.practica.pokemonDetailActivity;
import com.squareup.picasso.Picasso;

import java.util.List;

public class adapterPokemon extends RecyclerView.Adapter<adapterPokemon.viewHolderPokemon> {

    private final List<classPokemon> list;
    private final Context mContext;

    public adapterPokemon(List<classPokemon> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public viewHolderPokemon onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new viewHolderPokemon(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.pokemones, parent, false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull adapterPokemon.viewHolderPokemon holder, int position) {

        classPokemon pokemon = list.get(position);
        Picasso.get().load(pokemon.getUrl_imagen()).into(holder.image_anime);
        holder.text_anime.setText(pokemon.getNombre());
        holder.text_description.setText(pokemon.getTipo());

        holder.button_detail.setOnClickListener(view -> {
            Intent intent = new Intent(mContext, pokemonDetailActivity.class);
            intent.putExtra("poke", pokemon);
            mContext.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class viewHolderPokemon extends RecyclerView.ViewHolder {

        ImageView image_anime;
        TextView text_anime, text_description;
        Button button_detail;

        public viewHolderPokemon(@NonNull View itemView) {
            super(itemView);

            image_anime = itemView.findViewById(R.id.image_anime);
            button_detail = itemView.findViewById(R.id.button_detail);
            text_anime = itemView.findViewById(R.id.text_anime);
            text_description = itemView.findViewById(R.id.text_description);
        }
    }
}
