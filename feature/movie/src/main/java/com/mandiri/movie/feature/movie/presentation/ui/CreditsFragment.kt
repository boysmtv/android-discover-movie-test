/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: CreditsFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.model.CreditsModel
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.feature.movie.adapter.CreditsAdapter
import com.mandiri.movie.feature.movie.databinding.FragmentCreditsBinding
import com.mandiri.movie.feature.movie.presentation.viewmodel.CreditsViewModel
import dagger.hilt.android.AndroidEntryPoint

enum class CreditsCategory {
    CREW, CAST
}

@AndroidEntryPoint
class CreditsFragment(
    private val movieId: String,
    private val creditsCategory: CreditsCategory,
) : BaseFragment<FragmentCreditsBinding>(FragmentCreditsBinding::inflate) {

    private val castAdapter = CreditsAdapter.Cast { }
    private val crewAdapter = CreditsAdapter.Crew { }

    private val viewModel: CreditsViewModel by viewModels()

    override fun setupView() {
        subscribeCredits()
        setupAdapter()
        setupInit()
    }

    private fun subscribeCredits() = with(binding) {
        viewModel.creditsMovie.launch(this@CreditsFragment) {
            when (it) {
                is Result.Waiting -> {}

                is Result.Loading -> {
                    viewAnimator.displayedChild = 0
                }

                is Result.Success -> {
                    viewAnimator.displayedChild = 1
                    loadContent(it.data)
                }

                is Result.Error -> {
                    viewAnimator.displayedChild = 2
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
        rvCrew.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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

}