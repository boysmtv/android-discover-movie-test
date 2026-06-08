/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: RecentFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.model.FavouriteDataModel
import com.mandiri.movie.core.model.RecentDataModel
import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.core.utilities.Constant.THREE
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.core.utilities.hide
import com.mandiri.movie.core.utilities.show
import com.mandiri.movie.feature.common.viewmodel.UserViewModel
import com.mandiri.movie.feature.movie.adapter.RecentAdapter
import com.mandiri.movie.feature.movie.databinding.FragmentRecentBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecentFragment : BaseFragment<FragmentRecentBinding>(FragmentRecentBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private val recentAdapter = RecentAdapter(this::onMovieClicked)

    @Inject
    lateinit var parentNavigator: ParentNavigator

    override fun setupView() {
        setupAdapter()
        subscribeUser()
    }

    private fun subscribeUser() = with(viewModel) {
        fetchUserFromDatastore().launch(this@RecentFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = {
                    setupSubmitAdapter(it.recent)
                },
            )
        }
    }

    private fun setupAdapter() = with(binding) {
        rvRecent.apply {
            layoutManager = GridLayoutManager(activity, THREE)
            adapter = recentAdapter
        }
    }

    private fun setupSubmitAdapter(recent: MutableList<RecentDataModel>) = with(binding) {
        recentAdapter.submitData(recent)
        updateUiOrEmptyList(recent)
    }

    private fun updateUiOrEmptyList(favourite: MutableList<RecentDataModel>) = with(binding) {
        if (favourite.isNotEmpty()) {
            tvEmptyRecent.hide()
            rvRecent.show()
        } else {
            tvEmptyRecent.show()
            rvRecent.hide()
        }
    }

    private fun onMovieClicked(item: RecentDataModel) {
        parentNavigator.fromRecentToDetail(
            fragment = this,
            movieId = item.id.toString()
        )
    }

}