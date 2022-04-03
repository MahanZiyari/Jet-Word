package com.mahan.compose.jetword.di

import com.mahan.compose.jetword.Repository.WordRepository
import com.mahan.compose.jetword.network.WordApi
import com.mahan.compose.jetword.util.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn (SingletonComponent::class)
object AppModule {


    @Singleton
    @Provides
    fun provideWordApi(): WordApi = Retrofit.Builder()
        .baseUrl(Constants.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(WordApi::class.java)

    @Singleton
    @Provides
    fun provideRepository(api: WordApi) = WordRepository(api)
}