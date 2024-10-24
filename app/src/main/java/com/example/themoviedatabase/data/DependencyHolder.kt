package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.BASE_URL
import com.example.themoviedatabase.data.datasource.retrofit.RetrofitDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class DependencyHolder {

    @Provides
    fun provideRepository(dataSource: RetrofitDataSource): Repository {
        return Repository(dataSource)
    }

    @Provides
    fun provideRetrofitBuilder(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}