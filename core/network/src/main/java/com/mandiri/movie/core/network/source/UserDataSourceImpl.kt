/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: UserDataSourceImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.network.source

import com.mandiri.movie.core.common.util.network.ResultCallback
import com.mandiri.movie.core.model.UserModel
import com.mandiri.movie.core.network.ApiFirebaseResources
import com.mandiri.movie.core.network.firebase.FirebaseClient
import com.mandiri.movie.core.network.firestore.FirestoreClient
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserDataSourceImpl @Inject constructor(
    private val firebaseClient: FirebaseClient,
    private val firestoreClient: FirestoreClient,
) : UserDataSource {

    override suspend fun storeUserToFirebase(
        model: UserModel,
        onSuccess: (String) -> Unit,
        onError: () -> Unit
    ) {
        firebaseClient.storeRequestToFirebase(
            data = model,
            firestoreTable = ApiFirebaseResources.USER,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override suspend fun <Z : Any> fetchUserFromFirebase(
        id: String,
        resources: Z,
        onSuccess: Z.() -> Unit,
        onError: (String) -> Unit
    ) = firebaseClient.fetchRequestFromFirebase(
        id = id,
        firestoreTable = ApiFirebaseResources.USER,
        resources = resources,
        onSuccess = onSuccess,
        onError = onError
    )


    // start region to firestore
    // ===============================================================

    override fun storeUserToFirestore(
        id: String,
        model: UserModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.storeRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T> updateUserToFirestore(
        id: String,
        model: Map<String, T>,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.updateRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun fetchUserFromFirestore(
        filter: HashMap<String, String>,
        onLoad: () -> Unit,
        onSuccess: (UserModel) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.fetchUserFromFirestore(
            filter = filter,
            collection = ApiFirebaseResources.USER,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

    override fun <T : Any> fetchUserFromFirestore(
        filter: Pair<String, String>,
        resources: T
    ): Flow<ResultCallback<T>> {
        return firestoreClient.fetchDataFromFirestoreAsync(
            filter = filter,
            resources = resources,
            collection = ApiFirebaseResources.USER
        )
    }

    override fun <T : Any> updateUserToFirestoreAsync(
        id: String,
        model: Map<String, T>
    ): Flow<ResultCallback<String>> {
        return firestoreClient.updateRequestToFirestoreAsync(
            id = id,
            data = model,
            collection = ApiFirebaseResources.USER
        )
    }

}