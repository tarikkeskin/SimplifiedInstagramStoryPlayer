package com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters

import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.Story
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.StoryItemsBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.presentation.story.StoryFragmentViewModel
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.gone
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.visible


class StoryAdapter(private val mContext: Context, private val storyList: ArrayList<Story>,private val viewModel: StoryFragmentViewModel) :
    RecyclerView.Adapter<StoryAdapter.StoryViewHolder>() {

    inner class StoryViewHolder(binding: StoryItemsBinding) :
        RecyclerView.ViewHolder(binding.root) {
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

        if (!story.isVideo) {
            binding.videoView.gone()
            binding.imageView.visible()
            viewModel.setVideoDuration(5000)
            Picasso.get().load(story.url).into(binding.imageView)

        } else {
            binding.videoView.visible()
            binding.imageView.gone()

            var controller = MediaController(mContext)
            binding.videoView.setMediaController(controller)
            binding.videoView.setVideoPath(story.url)

            binding.videoView.setOnPreparedListener(object :MediaPlayer.OnPreparedListener{
                override fun onPrepared(p0: MediaPlayer?) {
                    viewModel.setVideoDuration(binding.videoView.duration.toLong())
                    binding.videoView.requestFocus()
                    binding.videoView.start()
                }

            })




        }

    }

    override fun getItemCount(): Int {
        return storyList.size
    }

}