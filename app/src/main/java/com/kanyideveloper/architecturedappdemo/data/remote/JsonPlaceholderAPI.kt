package com.kanyideveloper.architecturedappdemo.data.remote

import com.kanyideveloper.architecturedappdemo.model.Post
import retrofit2.http.GET

interface JsonPlaceholderAPI {

    @GET("posts")
    suspend fun getPosts(): List<Post>
}