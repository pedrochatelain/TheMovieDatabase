package com.example.themoviedatabase.data.dto

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String,
    @SerializedName("title") val title: String,
    @SerializedName("poster_path") val image: String,
    @SerializedName("release_date") val fecha_lanzamiento: String
){
    override fun toString(): String {
        return "Language: $originalLanguage - Original title: $originalTitle"
    }

    fun getAnioLanzamiento(): Int {
        return fecha_lanzamiento.take(4).toInt()
    }

}