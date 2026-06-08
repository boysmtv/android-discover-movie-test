/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MovieBannerAdapter.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.adapter

import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions.bitmapTransform
import com.mandiri.movie.core.model.MovieDataModel
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.Constant.FIFTEEN
import com.mandiri.movie.core.utilities.Constant.FIVE
import com.mandiri.movie.core.utilities.Constant.ONE
import com.mandiri.movie.core.utilities.Constant.SEVEN
import com.mandiri.movie.core.utilities.Constant.SEVENTY_FIVE
import com.mandiri.movie.core.utilities.Constant.THREE
import com.mandiri.movie.core.utilities.Constant.TWO
import com.mandiri.movie.core.utilities.Constant.ZERO
import com.mandiri.movie.feature.movie.R
import com.mandiri.movie.feature.movie.databinding.BannerHomeItemBinding
import com.zhpan.bannerview.BaseBannerAdapter
import com.zhpan.bannerview.BaseViewHolder
import jp.wasabeef.glide.transformations.BlurTransformation

typealias OnClickBannerMovie = (MovieDataModel) -> Unit

class MovieBannerAdapter(private val onClickBannerMovie: OnClickBannerMovie) : BaseBannerAdapter<MovieDataModel>() {

    override fun bindData(
        holder: BaseViewHolder<MovieDataModel>,
        item: MovieDataModel?,
        position: Int,
        pageSize: Int
    ) {
        if (holder is ViewHolder && item != null) {
            with(holder.viewBinding) {
                val posterUrl = "${Constant.BASE_URL_IMAGE_500}${item.posterPath}"

                Glide.with(ivItemHomeThumbnail.context)
                    .load(posterUrl)
                    .into(ivItemHomeThumbnail)

                Glide.with(ivItemHomeBlur.context)
                    .load(posterUrl)
                    .apply(bitmapTransform(BlurTransformation(SEVENTY_FIVE, THREE)))
                    .into(ivItemHomeBlur)

                val isShortTitle = (item.originalTitle?.length ?: ZERO) <= FIFTEEN
                tvItemHomeTitle.maxLines = if (isShortTitle) ONE else TWO
                tvItemHomeDesc.maxLines = if (isShortTitle) SEVEN else FIVE

                tvItemHomeTitle.text = item.originalTitle
                tvItemHomeDesc.text = item.overview

                btnClickHere.setOnClickListener {
                    onClickBannerMovie(item)
                }
            }
        }
    }

    override fun getLayoutId(viewType: Int): Int {
        return R.layout.banner_home_item
    }

    override fun createViewHolder(
        parent: ViewGroup,
        itemView: View,
        viewType: Int
    ): BaseViewHolder<MovieDataModel> {
        return ViewHolder(BannerHomeItemBinding.bind(itemView))
    }

    inner class ViewHolder(var viewBinding: BannerHomeItemBinding) :
        BaseViewHolder<MovieDataModel>(viewBinding.root)
}
