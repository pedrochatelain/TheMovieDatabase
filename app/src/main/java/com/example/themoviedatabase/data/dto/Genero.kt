package com.example.themoviedatabase.data.dto

import com.google.gson.annotations.SerializedName

data class Genero(@SerializedName("name") val genero: String) {
    override fun toString(): String {
        return genero;
    }
}