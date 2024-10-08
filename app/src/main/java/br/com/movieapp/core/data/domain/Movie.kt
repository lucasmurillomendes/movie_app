package br.com.movieapp.core.data.domain

data class Movie(
    val movieId: Int,
    val title: String,
    val voteAverage: Double = 0.0,
    val imageUrl: String = ""
)
