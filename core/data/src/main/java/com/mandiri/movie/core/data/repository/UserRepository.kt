/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserRepository.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.data.repository

import com.mandiri.movie.core.common.util.network.Result
import com.mandiri.movie.core.common.util.network.ResultCallback
import com.mandiri.movie.core.model.BaseResponse
import com.mandiri.movie.core.model.LoginModel
import com.mandiri.movie.core.model.RegisterModel
import com.mandiri.movie.core.model.UserModel
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    // start region to firebase
    // ===============================================================

    fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ): Flow<Result<Unit>>

    fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>


    // start region to firestore
    // ===============================================================

    fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>

    fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ): Flow<Result<Any?>>


    fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>>

    fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>,
    ): Flow<ResultCallback<String>>

}