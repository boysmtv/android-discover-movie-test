/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserRepositoryImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.ResultCallback
import com.mandiri.movie.core.common.util.network.execute
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.network.source.UserDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val network: UserDataSource,
) : UserRepository {

    override fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) = flow {
        emit(
            execute {
                network.storeUserToFirebase(model, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchUserFromFirebase(id, resources, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)


    // start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.storeUserToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.updateUserToFirestore(id, model, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) = flow {
        emit(
            execute {
                network.fetchUserFromFirestore(filter, onLoad, onSuccess, onError)
            }
        )
    }.flowOn(Dispatchers.IO)

    override fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>> = network.fetchUserFromFirestore(filter, resources)

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> = network.updateUserToFirestoreAsync(id, model)

}