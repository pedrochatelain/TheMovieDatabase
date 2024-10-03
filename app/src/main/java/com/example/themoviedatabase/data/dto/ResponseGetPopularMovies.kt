package com.example.themoviedatabase.data.dto

class ResponseGetPopularMovies(var movies: List<Movie>, var httpCode: Int, var isSuccessful: Boolean) {

    constructor(httpCode: Int, isSuccessful: Boolean) : this(
        emptyList<Movie>(),
        httpCode = httpCode,
        isSuccessful = isSuccessful
    )
}
