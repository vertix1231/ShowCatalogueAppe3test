package com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.listui.movie

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.databinding.ItemRowFilm2Binding
import com.dicoding.bangkit.android.jetpack.showcatalogueapp.ui.detail.DetailActivity
import com.topanlabs.filmtopan.data.Result



class FilmAdapter : RecyclerView.Adapter<FilmAdapter.FilmViewHolder>() {
    private val listFilms = ArrayList<Result>()

    fun setData(listFilms: List<Result>) {
        this.listFilms.clear()
        this.listFilms.addAll(listFilms)
        notifyDataSetChanged()
    }

    class FilmViewHolder(private val binding: ItemRowFilm2Binding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(film: Result) {
            with(binding) {
                tvYear.text = film.releaseDate.subSequence(0, 4)
                Glide
                    .with(imgView.context)
                    .load("https://image.tmdb.org/t/p/original/${film.posterPath}")
                    .into(imgView)
                itemView.setOnClickListener {
                    val intent = Intent(itemView.context, DetailActivity::class.java)
                    intent.putExtra(DetailActivity.TYPE_TAG, DetailActivity.ID_FILM)
                    intent.putExtra(DetailActivity.ID_TAG, film.id)
                    itemView.context.startActivity(intent)
                }
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val binding = ItemRowFilm2Binding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val film = listFilms[position]
        holder.bind(film)
    }

    override fun getItemCount(): Int {
        return listFilms.count()
    }
}