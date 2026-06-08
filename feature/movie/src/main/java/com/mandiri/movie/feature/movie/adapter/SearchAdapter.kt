/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SearchAdapter.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.mandiri.movie.core.common.util.listener.OnClickMovie
import com.mandiri.movie.core.data.callback.MovieCallback
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.Constant.FIVE_FLOAT
import com.mandiri.movie.core.utilities.Constant.THIRTY_FLOAT
import com.mandiri.movie.feature.movie.R
import com.mandiri.movie.feature.movie.databinding.MovieSearchItemBinding

class SearchAdapter(private val onClickMovie: OnClickMovie) :

    PagingDataAdapter<MovieDataModel, SearchAdapter.ViewHolder>(MovieCallback()) {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        item?.let { holder.bind(it) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            MovieSearchItemBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            ), onClickMovie
        )
    }

    inner class ViewHolder(
        private val binding: MovieSearchItemBinding,
        private val onClickMovie: OnClickMovie
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: MovieDataModel) {
            binding.apply {
                tvTitle.text = item.originalTitle

                thumbnail.load("${Constant.BASE_URL_IMAGE_200}${item.posterPath}") {
                    val context = root.context
                    val circularProgressDrawable = CircularProgressDrawable(context).apply {
                        strokeWidth = FIVE_FLOAT
                        centerRadius = THIRTY_FLOAT
                        strokeCap = Paint.Cap.BUTT
                        start()
                    }
                    placeholder(circularProgressDrawable)
                    error(R.drawable.ic_baseline_broken_image_24)
                }

                root.setOnClickListener {
                    onClickMovie(item)
                }
            }
        }

    }

}