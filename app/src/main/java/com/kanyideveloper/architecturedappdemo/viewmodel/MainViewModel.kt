package com.kanyideveloper.architecturedappdemo.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kanyideveloper.architecturedappdemo.data.repository.PostsRepository
import com.kanyideveloper.architecturedappdemo.model.Post
import com.kanyideveloper.architecturedappdemo.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: PostsRepository
) : ViewModel() {

    private val _posts = MutableLiveData<Resource<List<Post>>>()
    val posts: LiveData<Resource<List<Post>>> = _posts

    init {
        getPosts()
    }

    private fun getPosts(){
        _posts.value = Resource.Loading()
        viewModelScope.launch {
            _posts.value = repository.getPosts()
        }
    }
}