package com.tarikkeskin.simplifiedinstagramstoryplayer.common

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.Story
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.StoryItemsBinding

class StoryAdapter(private val mContext: Context, private val storyList: ArrayList<Story>):RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(binding:StoryItemsBinding):RecyclerView.ViewHolder(binding.root) {
        var binding: StoryItemsBinding

        init {
            this.binding = binding
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoryViewHolder {
        val layoutInflater = LayoutInflater.from(mContext)
        val binding: StoryItemsBinding = DataBindingUtil.inflate(layoutInflater,
            R.layout.story_items, parent, false)
        return StoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StoryViewHolder, position: Int) {
        val story = storyList[position]
        val binding = holder.binding

        Picasso.get().load(story.url).into(binding.imageView)
    }

    override fun getItemCount(): Int {
        return storyList.size
    }

}