/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ProfileFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.movie.presentation.ui

import android.graphics.Paint
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import coil.load
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.google.GoogleSignInExt
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.model.AuthMethod
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.nav.navigator.MenuNavigator
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.Constant.FIVE_FLOAT
import com.mandiri.movie.core.utilities.Constant.THIRTY_FLOAT
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.feature.movie.R
import com.mandiri.movie.feature.movie.databinding.FragmentProfileBinding
import com.mandiri.movie.feature.movie.presentation.viewmodel.SettingViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : BaseFragment<FragmentProfileBinding>(FragmentProfileBinding::inflate) {

    private val viewModel: SettingViewModel by viewModels()

    @Inject
    lateinit var menuNavigator: MenuNavigator

    private var googleSignInExt: GoogleSignInExt = GoogleSignInExt({}, {})

    private lateinit var userModel: UserModel

    private var token: String = Constant.EMPTY_STRING

    override fun setupView() {
        init()
        loadProfile()
        loadToken()
        setupListener()
    }

    private fun init() {
        userModel = UserModel()
        googleSignInExt.initGoogle(requireContext())
    }

    private fun loadProfile() = with(viewModel) {
        fetchDataAuth().launch(this@ProfileFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = { data ->
                    data?.let {
                        updateUi(it)
                    }
                },
            )
        }
    }

    private fun loadToken() = with(viewModel) {
        fetchDataTokenFcm().launch(this@ProfileFragment) {event ->
            invokeDataStoreEvent(event,
                isFetched = { message ->
                    binding.etToken.apply {
                        setText(message)
                    }
                    token = message
                },
            )
        }
    }

    private fun setupListener() = with(binding) {

        btnLogout.setOnClickListener {
            if (userModel.method == AuthMethod.GOOGLE.name)
                googleSignInExt.signOut(
                    isSuccess = {
                        Toast.makeText(
                            /* context = */ requireContext(),
                            /* text = */ "Logout is success",
                            /* duration = */ Toast.LENGTH_SHORT
                        ).show()
                    },
                    isError = {
                        Toast.makeText(
                            /* context = */ requireContext(),
                            /* text = */ "Logout is error : $it",
                            /* duration = */ Toast.LENGTH_LONG
                        ).show()
                    }
                )
            viewModel.clearPreferences(token)
            menuNavigator.fromMenuToGreetings(requireActivity())
        }

    }

    private fun updateUi(model: UserModel) = with(binding) {
        userModel = model

        if (model.firstName != Constant.EMPTY_STRING) {
            etFirstName.setText(model.firstName)
            etLastName.setText(model.lastName)
            etEmail.setText(model.email)
        }

        ivImage.load(model.photoUrl) {
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
    }

}