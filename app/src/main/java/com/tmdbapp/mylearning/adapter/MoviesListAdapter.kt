package com.tmdbapp.mylearning.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.tmdbapp.mylearning.R
import com.tmdbapp.mylearning.extensions.toDateInMMMDYYYYFormat
import com.tmdbapp.mylearning.mapper.MovieView
import com.tmdbapp.mylearning.utils.Constants

class MoviesListAdapter() : RecyclerView.Adapter<MoviesViewHolder>() {
    private var moviesList = ArrayList<MovieView>()

    fun addItems(items : List<MovieView>){
        moviesList.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_poster, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return moviesList.size
    }

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        return holder.bind(moviesList[position])
    }
}

class MoviesViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val photo: ImageView = itemView.findViewById(R.id.ivMoviePoster)
    private val title: TextView = itemView.findViewById(R.id.tvTitle)
    private val releaseDate: TextView = itemView.findViewById(R.id.tvReleaseDate)

    fun bind(movieView: MovieView) {
        movieView.thumbnail?.let {
            Glide.with(itemView.context).load(Constants.IMAGE_BASE_URL + it).into(photo)
        }
        title.text = movieView.name
        releaseDate.text = movieView.releaseDate.toDateInMMMDYYYYFormat()
    }

}
