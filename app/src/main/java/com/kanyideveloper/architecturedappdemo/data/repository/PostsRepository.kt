package com.kanyideveloper.architecturedappdemo.data.repository

import com.kanyideveloper.architecturedappdemo.data.remote.JsonPlaceholderAPI
import com.kanyideveloper.architecturedappdemo.model.Post
import com.kanyideveloper.architecturedappdemo.util.Resource
import javax.inject.Inject

class PostsRepository @Inject constructor(private val api: JsonPlaceholderAPI) {
    suspend fun getPosts(): Resource<List<Post>> {
        val response = try {
            api.getPosts()
        } catch (e: Exception) {
            return Resource.Error("Unknown error occurred")
        }
        return Resource.Success(response)
    }
}