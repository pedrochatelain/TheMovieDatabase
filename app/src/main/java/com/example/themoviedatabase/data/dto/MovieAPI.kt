package com.example.themoviedatabase.data.dto

import com.google.gson.annotations.SerializedName

data class MovieAPI(@SerializedName("results") val movies : List<Movie>)