package com.kanyideveloper.architecturedappdemo.di

import com.kanyideveloper.architecturedappdemo.data.remote.JsonPlaceholderAPI
import com.kanyideveloper.architecturedappdemo.data.repository.PostsRepository
import com.kanyideveloper.architecturedappdemo.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesJsonPlaceholderAPI(): JsonPlaceholderAPI {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderAPI::class.java)
    }

    @Provides
    @Singleton
    fun providesPostRepository(api: JsonPlaceholderAPI): PostsRepository {
        return PostsRepository(api)
    }
}