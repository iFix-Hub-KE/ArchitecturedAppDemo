package com.kanyideveloper.architecturedappdemo.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kanyideveloper.architecturedappdemo.databinding.PostRowBinding
import com.kanyideveloper.architecturedappdemo.model.Post

class PostsAdapter : ListAdapter<Post, PostsAdapter.MyViewHolder>(COMPARATOR) {

    object COMPARATOR : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    inner class MyViewHolder(private val binding: PostRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(post: Post?) {
            binding.textViewTitle.text = post?.title
            binding.textViewDescription.text = post?.body
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            PostRowBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val post = getItem(position)
        holder.bind(post)
    }
}