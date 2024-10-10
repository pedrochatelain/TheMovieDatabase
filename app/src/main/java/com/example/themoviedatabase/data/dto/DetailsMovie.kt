package com.example.themoviedatabase.data.dto

import androidx.compose.ui.graphics.ImageBitmap
import com.example.themoviedatabase.data.dto.api.DetailsMovieAPI

data class DetailsMovie(
    val titulo_original: String,
    val titulo: String,
    val portada: String?,
    val resumen: String,
    val generos: List<Genero>,
    var actores: MutableList<Actor>,
    val rating: Double,
    val fecha_lanzamiento: String,
    var image: ImageBitmap?
) {
    constructor(details: DetailsMovieAPI) : this (
        details.titulo_original,
        details.titulo,
        details.portada,
        details.resumen,
        details.generos,
        mutableListOf(),
        details.rating,
        details.fecha_lanzamiento,
        null
    )
}