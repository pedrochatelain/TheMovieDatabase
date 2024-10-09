package com.example.themoviedatabase.data

import com.example.themoviedatabase.data.datasource.retrofit.Retrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ModuleContainer {

    @Provides
    fun provideRepository(): Repository {
        return Repository(Retrofit())
    }

}