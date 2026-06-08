/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserUseCaseImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.domain

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.common.util.network.ResultCallback
import com.mandiri.movie.core.data.repository.UserRepository
import com.mandiri.movie.core.model.BaseResponse
import com.mandiri.movie.core.model.LoginModel
import com.mandiri.movie.core.model.RegisterModel
import com.mandiri.movie.core.model.UserModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserUseCaseImpl @Inject constructor(
    private val repository: UserRepository
) : UserUseCase {

    override fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>> = repository.storeUserToFirebase(model, onSuccess, onError)

    override fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.fetchUserFromFirebase(id, resources, onSuccess, onError)


    // start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.storeUserToFirestore(id, model, onLoad, onSuccess, onError)

    override fun <T> updateUserToFirestore(
        id: String,
        filter: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.updateUserToFirestore(id, filter, onLoad, onSuccess, onError)

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>> = repository.fetchUserFromFirestore(filter, onLoad, onSuccess, onError)

    override fun <T : Any> fetchUserFromFirestoreAsync(
        filter: Pair<String, String>,
        resources: T,
    ): Flow<ResultCallback<T>> = repository.fetchUserFromFirestore(filter, resources)

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> = repository.updateUserToFirestoreAsync(id, model)

}