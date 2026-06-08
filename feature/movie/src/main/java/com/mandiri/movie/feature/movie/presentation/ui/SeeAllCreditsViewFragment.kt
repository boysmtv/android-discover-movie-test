/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: SeeAllCreditsViewFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.CastModel
import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.model.CrewModel
import com.mandiri.movie.core.utilities.Constant.ONE
import com.mandiri.movie.core.utilities.Constant.THREE
import com.mandiri.movie.core.utilities.Constant.TWO
import com.mandiri.movie.core.utilities.Constant.ZERO
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.feature.movie.adapter.SeeAllCreditsAdapter
import com.mandiri.movie.feature.movie.databinding.SeeAllCreditsListBinding
import com.mandiri.movie.feature.movie.presentation.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SeeAllCreditsViewFragment(
    private val movieId: String,
    private val creditsCategory: CreditsCategory,
) : BaseFragment<SeeAllCreditsListBinding>(SeeAllCreditsListBinding::inflate) {

    private val castAdapter = SeeAllCreditsAdapter.Cast(this::onClickCreditsCast)
    private val crewAdapter = SeeAllCreditsAdapter.Crew(this::onClickCreditsCrew)

    private val viewModel: CreditsViewModel by viewModels()

    override fun setupView() {
        subscribeCredits()
        setupAdapter()
        setupInit()
    }

    private fun subscribeCredits() = with(binding) {
        viewModel.creditsMovie.launch(this@SeeAllCreditsViewFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> {
                    viewAnimator.displayedChild = ZERO
                }

                is Result.Success -> {
                    viewAnimator.displayedChild = ONE
                    loadContent(it.data)
                }

                is Result.Error -> {
                    viewAnimator.displayedChild = TWO
                    showErrorView(
                        it.throwable.message
                            ?: "Error occurred when fetching data from server. Please try again"
                    )
                }
            }
        }
    }

    private fun loadContent(it: CreditsModel) {
        if (checkCreditsIsCrew(creditsCategory))
            crewAdapter.submitList(it.crew)
        else
            castAdapter.submitList(it.cast)
    }

    private fun setupInit() {
        viewModel.getCredits(movieId)
    }

    private fun setupAdapter() = with(binding) {
        rvSeeAllCredits.apply {
            layoutManager = GridLayoutManager(requireContext(), THREE, RecyclerView.VERTICAL, false)
            adapter = if (checkCreditsIsCrew(creditsCategory)) crewAdapter else castAdapter
        }
    }

    private fun showErrorView(message: String) {
        binding.viewCommonError.apply {
            errorMessage.text = message
            buttonRetry.setOnClickListener { setupInit() }
        }
    }

    private fun checkCreditsIsCrew(creditsCategory: CreditsCategory): Boolean {
        return creditsCategory.name == CreditsCategory.CREW.name
    }

    private fun onClickCreditsCrew(item: CrewModel) {
        // will be implement
    }

    private fun onClickCreditsCast(item: CastModel) {
        // will be implement
    }

}