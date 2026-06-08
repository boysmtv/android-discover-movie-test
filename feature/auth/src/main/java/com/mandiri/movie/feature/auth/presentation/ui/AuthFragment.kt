/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: AuthFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.core.utilities.Constant.EIGHT
import com.mandiri.movie.core.utilities.Constant.EMAIL_MESSAGE
import com.mandiri.movie.core.utilities.Constant.NINE
import com.mandiri.movie.core.utilities.Constant.PASSWORD_MESSAGE
import com.mandiri.movie.core.utilities.Constant.WARNING_MESSAGE
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.core.utilities.validateEmail
import com.mandiri.movie.core.utilities.validateInput
import com.mandiri.movie.feature.auth.databinding.FragmentAuthBinding
import com.mandiri.movie.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : BaseFragment<FragmentAuthBinding>(FragmentAuthBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var parentNavigator: ParentNavigator

    private var userModel: UserModel = UserModel()

    override fun setupView() {
        setAutoText()
        setupListener()
    }

    private fun setAutoText() = with(binding) {
        etEmail.setText("Boys.mtv@gmail.com")
        etPassword.setText("123456789")
    }

    private fun setupListener() = with(binding) {
        btnLogin.setOnClickListener {
            if (validateInput()) {
                userModel.apply {
                    email = etEmail.text.toString()
                    password = etPassword.text.toString()
                }
                getUserFromFirestore()
            }
        }

        tvRegister.setOnClickListener {
            parentNavigator.fromAuthToRegister(this@AuthFragment)
        }

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun getUserFromFirestore() {
        viewModel.fetchUserFromFirestore(
            filter = hashMapOf(
                "email" to userModel.email!!,
            ),
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                if (it.password != null) {
                    if (it.password.equals(userModel.password)) {
                        userModel = it
                        saveUserToDataStore()
                    } else
                        showDialogGeneralError("Login failed", "Invalid username or password")
                } else
                    showDialogGeneralError("Login failed", "Error get profile")
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Login failed", it)
            }
        )
    }

    private fun saveUserToDataStore() {
        viewModel.storeUserToDatastore(
            jsonUtil.toJson(userModel)
        ).launch(this@AuthFragment) { event ->
            invokeDataStoreEvent(
                event,
                isStored = {
                    parentNavigator.fromAuthToMenu(fragment = this@AuthFragment)
                }
            )
        }
    }

    private fun validateInput(): Boolean = with(binding) {
        val email = etEmail.validateEmail(EIGHT, EMAIL_MESSAGE)
        if (!email.first) {
            showDialogGeneralError(WARNING_MESSAGE, email.second)
            return false
        }

        val password = etPassword.validateInput(NINE, PASSWORD_MESSAGE)
        if (!password.first) {
            showDialogGeneralError(WARNING_MESSAGE, password.second)
            return false
        }
        return true
    }

}