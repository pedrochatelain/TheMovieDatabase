package com.example.themoviedatabase.data.dto

import com.google.gson.annotations.SerializedName

data class Actor(
    @SerializedName("name") val nombre: String,
    @SerializedName("profile_path") val foto: String,
)