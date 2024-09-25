package com.example.themoviedatabase.data.dto

import com.google.gson.annotations.SerializedName

data class Movie(
    @SerializedName("original_language") val originalLanguage: String,
    @SerializedName("original_title") val originalTitle: String)
{
    override fun toString(): String {
        return "Language: $originalLanguage - Original title: $originalTitle"
    }
}