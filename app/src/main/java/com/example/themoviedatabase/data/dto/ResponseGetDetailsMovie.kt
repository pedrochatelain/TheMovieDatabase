package com.example.themoviedatabase.data.dto

class ResponseGetDetailsMovie(
    var movie: DetailsMovie?,
    var isSuccessful: Boolean,
    var errorConnection: Boolean
) {

    companion object Factory {

        fun success(movie: DetailsMovie): ResponseGetDetailsMovie {
            return ResponseGetDetailsMovie(
                movie = movie,
                isSuccessful = true,
                errorConnection = false
            )
        }

        fun failure(): ResponseGetDetailsMovie {
            return ResponseGetDetailsMovie(
                movie = null,
                isSuccessful = false,
                errorConnection = false
            )
        }

        fun errorNetwork(): ResponseGetDetailsMovie {
            return ResponseGetDetailsMovie(
                movie = null,
                isSuccessful = false,
                errorConnection = true
            )
        }
    }




}
