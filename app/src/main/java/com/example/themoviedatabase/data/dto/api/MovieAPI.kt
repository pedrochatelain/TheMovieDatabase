package com.example.themoviedatabase.data.dto.api

import com.example.themoviedatabase.data.dto.Movie
import com.google.gson.annotations.SerializedName

data class MovieAPI(@SerializedName("results") val movies : List<Movie>)