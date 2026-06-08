/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: GreetingsFragment.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.auth.presentation.ui

import android.content.Context
import android.content.Intent
import androidx.activity.OnBackPressedCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.tasks.Task
import com.mandiri.movie.core.common.base.BaseFragment
import com.mandiri.movie.core.common.google.GoogleSignInExt
import com.mandiri.movie.core.common.util.LocationUtil
import com.mandiri.movie.core.common.util.TransactionUtil
import com.mandiri.movie.core.common.util.event.invokeDataStoreEvent
import com.mandiri.movie.core.common.util.listener.EventListener
import com.mandiri.movie.core.model.AuthMethod
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.nav.navigator.ParentNavigator
import com.mandiri.movie.core.ui.dialog.base.BaseDataDialog
import com.mandiri.movie.core.utilities.Constant
import com.mandiri.movie.core.utilities.extension.launch
import com.mandiri.movie.feature.auth.R
import com.mandiri.movie.feature.auth.databinding.FragmentGreetingsBinding
import com.mandiri.movie.feature.auth.presentation.viewmodel.UserViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class GreetingsFragment : BaseFragment<FragmentGreetingsBinding>(FragmentGreetingsBinding::inflate) {

    private val viewModel: UserViewModel by viewModels()

    private var googleSignInExt: GoogleSignInExt =
        GoogleSignInExt(
            callbackGoogleSignInSuccess = this::invokeGoogleSignInSuccess,
            callbackGoogleSignInError = this::invokeGoogleSignInError
        )

    @Inject
    lateinit var parentNavigator: ParentNavigator

    private var userModel: UserModel = UserModel()

    private var token: String = Constant.EMPTY_STRING

    private val transactionId = TransactionUtil.generateTransactionID()

    private lateinit var locationUtil: LocationUtil

    private lateinit var eventListener: EventListener

    override fun setupView() {
        init()
        fetchTokenFromDatastore()
        setupListener()
        setOnBackPressed()
    }

    private fun init() {
        googleSignInExt.initGoogle(requireContext())
        locationUtil = LocationUtil(context = requireContext())
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            eventListener = context as EventListener
        } catch (_: ClassCastException) {

        }
    }

    private fun setupListener() = with(binding) {
        btnFacebook.setOnClickListener {
            showDialogGeneralError(title = "Warning", message = "Under development, please try another way")
        }

        btnGoogle.setOnClickListener {
            if (locationUtil.isLocationPermissions() && locationUtil.isGpsLocationEnable())
                onClickSignInByGoogle()
            else showWarningLocation()
        }

        btnEmail.setOnClickListener {
            if (locationUtil.isLocationPermissions() && locationUtil.isGpsLocationEnable())
                parentNavigator.fromGreetingsToAuth(this@GreetingsFragment)
            else showWarningLocation()
        }
    }

    private fun showWarningLocation() {
        val content = BaseDataDialog(
            title = "Warning",
            content = "Please allow location to continue application",
            primaryButtonShow = true,
            secondaryButtonText = Constant.EMPTY_STRING,
            secondaryButtonShow = false,
            icon = R.drawable.ic_warning_rounded,
            primaryButtonText = "OK"
        )
        showDialogWithActionButton(
            dataToDialog = content,
            actionClickPrimary = {
                if (!locationUtil.isLocationPermissions()) eventListener.askLocationPermission()
                if (!locationUtil.isGpsLocationEnable()) eventListener.askGpsPermission()
            },
            tag = RegisterFragment::class.simpleName.toString()
        )
    }

    private fun onClickSignInByGoogle() = with(googleSignInExt) {
        val signInIntent: Intent = googleSignInClient.signInIntent
        startActivityIntent.launch(signInIntent)
    }

    private var startActivityIntent: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        it?.let {
            with(googleSignInExt) {
                val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(it.data)
                handleResult(task)
            }
        }
    }

    private fun invokeGoogleSignInSuccess(model: UserModel) {
        userModel = model
        getUserFromFirestore()
    }

    private fun invokeGoogleSignInError(message: String) {
        showDialogGeneralError("Google sign error", message)
    }

    private fun storeToDataStore(userModel: UserModel) {
        viewModel.storeUserToDatastore(jsonUtil.toJson(userModel))
            .launch(this@GreetingsFragment) { event ->
                invokeDataStoreEvent(event,
                    isStored = {
                        parentNavigator.fromGreetingsToMenu(this@GreetingsFragment)
                    }
                )
            }
    }

    private fun fetchTokenFromDatastore() = with(viewModel) {
        fetchTokenFromDatastore().launch(this@GreetingsFragment) { event ->
            invokeDataStoreEvent(event,
                isFetched = { message ->
                    token = message
                }
            )
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
                storeToDataStore(userModel)
            },
            onError = {
                showHideProgress(isLoading = false)
                storeUserToFirestore()
            }
        )
    }

    private fun storeUserToFirestore() {
        viewModel.storeUserToFirestore(
            id = transactionId,
            model = userModel.apply {
                id = transactionId
                idFireStore = Constant.EMPTY_STRING
                phone = Constant.UNDERSCORE
                password = Constant.UNDERSCORE
                method = AuthMethod.GOOGLE.name
            },
            onLoad = {
                showHideProgress(isLoading = true)
            },
            onSuccess = {
                showHideProgress(isLoading = false)
                storeToDataStore(userModel)
            },
            onError = {
                showHideProgress(isLoading = false)
                showDialogGeneralError("Register failed", it)
            }
        )
    }

    private fun setOnBackPressed() {
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                onDestroy()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onDestroy() {
        super.onDestroy()
        requireActivity().finish()
    }
}