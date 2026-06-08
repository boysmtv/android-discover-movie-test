/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: VideoPlayerFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import android.app.Activity
import android.content.pm.ActivityInfo
import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.feature.movie.databinding.FragmentVideoPlayerBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import kotlinx.coroutines.launch

class VideoPlayerFragment : BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {

    private val args: VideoPlayerFragmentArgs by navArgs()

    override fun setupView() {
        loadArguments()
        forcePortrait()
    }

    private fun loadArguments() = with(binding) {
        lifecycle.addObserver(youtubePlayer)
        youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
                youTubePlayer.loadVideo(args.keyId, 0f)
            }
            override fun onError(
                youTubePlayer: YouTubePlayer,
                error: PlayerConstants.PlayerError
            ) {
                Log.e("Boys", "YouTube Error: $error")
            }
        })
    }

    private fun forcePortrait() {
        val a: Activity? = activity
        if (a != null) a.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
    }

}