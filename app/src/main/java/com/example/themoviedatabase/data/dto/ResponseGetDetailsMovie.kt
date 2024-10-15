package com.example.themoviedatabase.data.dto

class ResponseGetDetailsMovie(var movie: DetailsMovie?, var httpCode: Int, var isSuccessful: Boolean) {

    constructor(httpCode: Int, isSuccessful: Boolean) : this(
        null,
        httpCode = httpCode,
        isSuccessful = isSuccessful
    )
}
