package com.example.themoviedatabase.data.dto.api

import com.example.themoviedatabase.data.dto.Genero
import com.google.gson.annotations.SerializedName

data class DetailsMovieAPI(
    @SerializedName("original_title") val titulo_original: String,
    @SerializedName("title") val titulo: String,
    @SerializedName("backdrop_path") val portada: String,
    @SerializedName("overview") val resumen: String,
    @SerializedName("genres") val generos: List<Genero>,
    @SerializedName("vote_average") val rating: Double,
    @SerializedName("release_date") val fecha_lanzamiento: String
)