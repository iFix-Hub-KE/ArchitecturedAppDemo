package com.kanyideveloper.architecturedappdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.kanyideveloper.architecturedappdemo.adapter.PostsAdapter
import com.kanyideveloper.architecturedappdemo.databinding.ActivityMainBinding
import com.kanyideveloper.architecturedappdemo.util.Resource
import com.kanyideveloper.architecturedappdemo.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val adapter by lazy { PostsAdapter() }
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        subscribeToPostsObserver()

    }

    private fun subscribeToPostsObserver() {
        viewModel.posts.observe(this, Observer { result ->
            when (result) {
                is Resource.Loading -> {
                    binding.postsProgressBar.isVisible = true
                }
                is Resource.Success -> {
                    binding.postsProgressBar.isVisible = false
                    val posts = result.data
                    adapter.submitList(posts)
                    binding.postsRecyclerview.adapter = adapter
                }
                is Resource.Error -> {
                    binding.postsProgressBar.isVisible = false
                    Snackbar.make(binding.root, result.message.toString(), Snackbar.LENGTH_LONG)
                        .show()
                }
            }
        })
    }
}