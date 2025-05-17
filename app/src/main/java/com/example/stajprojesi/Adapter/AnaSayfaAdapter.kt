package com.example.stajprojesi.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.stajprojesi.Model.Film
import com.example.stajprojesi.databinding.FilmRecyclerRowBinding

class AnaSayfaAdapter(private var filmListesi: List<Film>, private val onItemClick: (Film) -> Unit ) : RecyclerView.Adapter<AnaSayfaAdapter.AnaSayfaHolder>()
{

    fun updateFilms(newFilms: List<Film>)
    {
        filmListesi = newFilms
        notifyDataSetChanged()
    }

    class AnaSayfaHolder(private val binding: FilmRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root)
    {
        fun bind(film: Film, onItemClick: (Film) -> Unit)
        {
            binding.textView.text = film.adi

            film.gorsel?.let { path ->

                Glide.with(binding.root.context)
                    .load("https://image.tmdb.org/t/p/w500$path")
                    .into(binding.imageView)
            }
            binding.root.setOnClickListener { onItemClick(film) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnaSayfaHolder
    {
        val binding = FilmRecyclerRowBinding.inflate (

            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AnaSayfaHolder(binding)
    }

    override fun getItemCount(): Int = filmListesi.size

    override fun onBindViewHolder(holder: AnaSayfaHolder, position: Int)
    {
        holder.bind(filmListesi[position], onItemClick)
    }
}