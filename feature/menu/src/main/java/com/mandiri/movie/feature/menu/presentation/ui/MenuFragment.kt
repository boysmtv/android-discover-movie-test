/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: MenuFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.menu.presentation.ui

import android.annotation.SuppressLint
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.feature.menu.R
import com.mandiri.movie.feature.menu.databinding.FragmentMenuBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MenuFragment : BaseFragment<FragmentMenuBinding>(FragmentMenuBinding::inflate) {

    private val tag = this::class.java.simpleName

    @Inject
    lateinit var parentNavigator: ParentNavigator

    override fun setupView() {
        setupInit()
    }

    @SuppressLint("CommitTransaction")
    private fun setupInit() {
        val fragment = childFragmentManager.findFragmentById(R.id.nav_host_menu_fragment_container) as NavHostFragment
        binding.bottomNavigationView.setupWithNavController(fragment.navController)
    }

}
