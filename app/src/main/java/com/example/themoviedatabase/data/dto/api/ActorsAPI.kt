package com.example.themoviedatabase.data.dto.api

import com.example.themoviedatabase.data.dto.Actor
import com.google.gson.annotations.SerializedName

data class ActorsAPI(
    @SerializedName("cast") val actores: List<Actor>,
)