package com.example.themoviedatabase.data.dto

import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI

data class DetailsMovie(
    val titulo: String,
    val portada: String,
    val resumen: String,
    val generos: List<Genero>,
    var actores: List<Actor>,
    val rating: Double
) {
    constructor(details: DetailsMovieAPI) : this(details.titulo, details.portada, details.resumen, details.generos, emptyList(), details.rating)
}