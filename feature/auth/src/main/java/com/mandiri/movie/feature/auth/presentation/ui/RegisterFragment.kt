/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: RegisterFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.auth.presentation.ui

import androidx.fragment.app.viewModels
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.util.InternetUtil
import com.mandiri.movie.core.common.util.LocationUtil
import com.mandiri.movie.core.common.util.NotificationUtil
import com.mandiri.movie.core.common.util.TransactionUtil
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.model.AuthMethod
import com.mandiri.movie.core.model.PermissionModel
import com.mandiri.movie.core.model.ProfileModel
import com.mandiri.movie.core.model.SettingModel
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.core.ui.dialog.base.BaseDataDialog
import com.mandiri.movie.core.utilities.Constant.EIGHT
import com.mandiri.movie.core.utilities.Constant.EMAIL_MESSAGE
import com.mandiri.movie.core.utilities.Constant.EMPTY_STRING
import com.mandiri.movie.core.utilities.Constant.FIRST_NAME_MESSAGE
import com.mandiri.movie.core.utilities.Constant.LAST_NAME_MESSAGE
import com.mandiri.movie.core.utilities.Constant.NINE
import com.mandiri.movie.core.utilities.Constant.PASSWORD_MESSAGE
import com.mandiri.movie.core.utilities.Constant.PHONE_MESSAGE
import com.mandiri.movie.core.utilities.Constant.TEN
import com.mandiri.movie.core.utilities.Constant.THREE
import com.mandiri.movie.core.utilities.Constant.WARNING_MESSAGE
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.core.utilities.validateEmail
import com.mandiri.movie.core.utilities.validateInput
import com.mandiri.movie.core.utilities.validatePhone
import com.mandiri.movie.feature.auth.R
import com.mandiri.movie.feature.auth.databinding.FragmentRegisterBinding
import com.mandiri.movie.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterFragment : BaseFragment<FragmentRegisterBinding>(FragmentRegisterBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    @Inject
    lateinit var parentNavigator: ParentNavigator

    private var userModel: UserModel = UserModel()

    private var token: String = EMPTY_STRING

    private val transactionId = TransactionUtil.generateTransactionID()

    override fun setupView() {
        setAutoText()
        init()
        fetchTokenFromDatastore()
        setupListener()
    }

    private fun setAutoText() = with(binding) {
        etFirstName.setText("Dedy")
        etLastName.setText("Wijaya")
        etPhone.setText("08989996305")
        etEmail.setText("Boys.mtv@gmail.com")
        etPassword.setText("123456789")
    }

    private fun init() = with(binding) {
        cvRegister.setBackgroundResource(R.drawable.card_rounded_top)
    }

    private fun fetchTokenFromDatastore() = with(viewModel) {
        fetchTokenFromDatastore().launch(this@RegisterFragment) { event ->
            invokeDataStoreEvent(
                event,
                isFetched = { message ->
                    token = message
                }
            )
        }
    }

    private fun setupListener() = with(binding) {
        tvLogin.setOnClickListener {
            parentNavigator.fromRegisterToAuth(this@RegisterFragment)
        }

        ivBack.setOnClickListener {
            requireActivity().onBackPressedDispatcher.onBackPressed()
        }

        btnRegister.setOnClickListener {
            if (validateInput()) {
                userModel.apply {
                    id = transactionId
                    idFireStore = EMPTY_STRING
                    idGoogle = EMPTY_STRING
                    idToken = token
                    firstName = etFirstName.text.toString()
                    lastName = etLastName.text.toString()
                    displayName = etFirstName.text.toString() + " " + etLastName.text.toString()
                    email = etEmail.text.toString()
                    phone = etPhone.text.toString()
                    photoUrl = EMPTY_STRING
                    password = etPassword.text.toString()
                    method = AuthMethod.EMAIL.name
                    profile = ProfileModel().apply {
                        connection = InternetUtil(requireContext()).getStatusConnectionModel()
                        permission = PermissionModel().apply {
                            location = LocationUtil(requireContext()).isLocationPermissions()
                            internet = InternetUtil(requireContext()).isNetworkAvailable()
                            notification = NotificationUtil(requireContext()).isNotificationEnabled()
                        }
                        setting = SettingModel(
                            login = true,
                            favourite = true,
                            notification = true
                        )
                        updatedAt = TransactionUtil.getTimestampWithFormat()
                    }
                }
                getUserFromFirestore()
            }
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
                userModel = it
                showDialogGeneralError("Register failed", "Data already created.")
            },
            onError = {
                showHideProgress(isLoading = false)
                storeUserToFirestore()
            }
        )
    }

    private fun storeUserToFirestore() = with(binding) {
        viewModel.storeUserToFirestore(
            id = transactionId,
            model = userModel,
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                showDialogSuccessRegister(userModel.displayName ?: EMPTY_STRING)
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Register failed", it)
            }
        )
    }

    private fun showDialogSuccessRegister(name: String) {
        val content = BaseDataDialog(
            title = "Welcome, $name",
            content = "Your account already success created",
            primaryButtonShow = true,
            secondaryButtonText = EMPTY_STRING,
            secondaryButtonShow = false,
            icon = R.drawable.ic_warning_rounded,
            primaryButtonText = "Login"
        )
        showDialogWithActionButton(
            dataToDialog = content,
            actionClickPrimary = {
                parentNavigator.fromRegisterToAuth(this@RegisterFragment)
            },
            tag = RegisterFragment::class.simpleName.toString()
        )
    }

    private fun validateInput(): Boolean = with(binding) {
        val firstname = etFirstName.validateInput(THREE, FIRST_NAME_MESSAGE)
        if (!firstname.first) {
            showDialogGeneralError(WARNING_MESSAGE, firstname.second)
            return false
        }
        val lastname = etLastName.validateInput(THREE, LAST_NAME_MESSAGE)
        if (!lastname.first) {
            showDialogGeneralError(WARNING_MESSAGE, lastname.second)
            return false
        }
        val phone = etPhone.validatePhone(TEN, PHONE_MESSAGE)
        if (!phone.first) {
            showDialogGeneralError(WARNING_MESSAGE, phone.second)
            return false
        }
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