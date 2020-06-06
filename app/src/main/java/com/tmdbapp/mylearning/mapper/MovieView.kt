package com.tmdbapp.mylearning.mapper

data class MovieView (val movieId: Int,
                 val description: String,
                 val thumbnail: String?,
                 val releaseDate: String,
                 val name: String)