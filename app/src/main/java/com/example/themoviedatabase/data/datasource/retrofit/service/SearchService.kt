package com.example.themoviedatabase.data.datasource.retrofit.service

import com.example.themoviedatabase.data.datasource.API_KEY
import com.example.themoviedatabase.data.datasource.SEARCH_URL
import com.example.themoviedatabase.data.dto.api.MovieAPI
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Url
import javax.inject.Inject

class SearchService @Inject constructor(retrofit: Retrofit) {

    private interface SearchService {
        @GET
        suspend fun search(@Url url: String) : Response<MovieAPI>
    }

    private val service = retrofit.create(SearchService::class.java)

    suspend fun search(titleMovie: String, page: Int): Response<MovieAPI> {
        val url = "${SEARCH_URL}movie?api_key=$API_KEY&query=$titleMovie&page=$page"
        return service.search(url)
    }

}