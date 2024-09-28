package com.example.themoviedatabase.data.dto

data class DetailsMovie(
    val titulo: String,
    val portada: String,
    val resumen: String,
    val generos: List<Genero>,
    val actores: List<Actor>,
    val rating: Int
)