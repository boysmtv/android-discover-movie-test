/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserViewModel.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.feature.auth.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mandiri.movie.core.common.util.event.DataStoreCacheEvent
import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.common.data.preferences.DataStorePreferences
import com.mandiri.movie.core.domain.UserUseCase
import com.mandiri.movie.core.model.BaseResponse
import com.mandiri.movie.core.model.LoginModel
import com.mandiri.movie.core.model.RegisterModel
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.utilities.PreferenceConstants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: UserUseCase,
    private val dataStore: DataStorePreferences
) : ViewModel() {

    fun storeUserToDatastore(user: String) =
        flow {
            dataStore.setString(
                PreferenceConstants.Authorization.PREF_USER,
                user
            )
            emit(DataStoreCacheEvent.StoreSuccess)
        }

    fun fetchUserFromDatastore() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_USER,
                    ).getOrNull().orEmpty()
                )
            )
        }

    fun fetchTokenFromDatastore() =
        flow {
            emit(
                DataStoreCacheEvent.FetchSuccess(
                    dataStore.getString(
                        PreferenceConstants.Authorization.PREF_FCM_TOKEN,
                    ).getOrNull().orEmpty()
                )
            )
        }


    // start region to firebase
    // ===============================================================

    fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        useCase.storeUserToFirebase(model, onSuccess, onError).launchIn(viewModelScope)
    }

    fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: (Z) -> Unit,
        onError: (String) -> Unit
    ) = useCase.fetchUserFromFirebase(
        id,
        resources,
        onSuccess,
        onError
    ).launchIn(viewModelScope)


    // start region to firestore
    // ===============================================================

    fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.storeUserToFirestore(
            id = id,
            model = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    fun updateUserToFirestore(
        id: String,
        model: Map<String, String>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        useCase.updateUserToFirestore(
            id = id,
            filter = model,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        ).launchIn(viewModelScope)
    }

    fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = useCase.fetchUserFromFirestore(
        filter = filter,
        onLoad = onLoad,
        onSuccess = onSuccess,
        onError = onError
    ).launchIn(viewModelScope)

}