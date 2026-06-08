/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieLoadStateAdapter.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.util.common

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.movie.core.utilities.hide
import com.mandiri.movie.feature.movie.databinding.ViewLoadStateBinding

class MovieLoadStateAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<MovieLoadStateAdapter.ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        return ViewHolder(
            ViewLoadStateBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    inner class ViewHolder(
        private val binding: ViewLoadStateBinding,
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            with(binding) {
                lyShimmer.root.hide()
                viewCommonError.apply {
                    buttonRetry.setOnClickListener { retry.invoke() }
                }
            }
        }

        fun bind(loadState: LoadState) {
            binding.apply {
                if (loadState is LoadState.Error) {
                    viewCommonError.errorMessage.text = loadState.error.localizedMessage
                }

                when (loadState) {
                    is LoadState.NotLoading -> {}
                    LoadState.Loading -> root.displayedChild = 0
                    is LoadState.Error -> root.displayedChild = 1
                }
            }
        }
    }
}