package com.tarikkeskin.simplifiedinstagramstoryplayer.common.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.tarikkeskin.simplifiedinstagramstoryplayer.R
import com.tarikkeskin.simplifiedinstagramstoryplayer.data.Story
import com.tarikkeskin.simplifiedinstagramstoryplayer.databinding.StoryItemsBinding
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.gone
import com.tarikkeskin.simplifiedinstagramstoryplayer.utils.visible


class StoryAdapter(private val mContext: Context, private val storyList: ArrayList<Story>) :
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
        var playWhenReady = true
        var currentItem = 0
        var playbackPosition = 0L
        var player:ExoPlayer? = null

        if (!story.isVideo) {
            binding.videoView.gone()
            binding.imageView.visible()
            Picasso.get().load(story.url).into(binding.imageView)
            player?.let { exoPlayer ->
                playbackPosition = exoPlayer.currentPosition
                currentItem = exoPlayer.currentMediaItemIndex
                playWhenReady = exoPlayer.playWhenReady
                exoPlayer.release()
            }
            player = null
        } else {
            binding.videoView.visible()
            binding.imageView.gone()


            player = ExoPlayer.Builder(mContext)
                .build()
                .also { exoPlayer ->
                    binding.videoView.player = exoPlayer
                    val mediaItem = MediaItem.fromUri(story.url)
                    exoPlayer.setMediaItem(mediaItem)
                    exoPlayer.playWhenReady = playWhenReady
                    exoPlayer.seekTo(currentItem, playbackPosition)
                    exoPlayer.repeatMode = ExoPlayer.REPEAT_MODE_ALL
                    exoPlayer.prepare()
                }


        }

    }

    override fun getItemCount(): Int {
        return storyList.size
    }

}