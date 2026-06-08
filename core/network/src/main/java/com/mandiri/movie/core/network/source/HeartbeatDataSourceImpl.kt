/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: HeartbeatDataSourceImpl.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.network.source

import com.mandiri.movie.core.model.HeartbeatModel
import com.mandiri.movie.core.network.ApiFirebaseResources
import com.mandiri.movie.core.network.firestore.FirestoreClient
import javax.inject.Inject

class HeartbeatDataSourceImpl @Inject constructor(
    private val firestoreClient: FirestoreClient,
) : HeartbeatDataSource {

    // start region to firestore
    // ===============================================================

    override fun storeHeartbeatToFirestore(
        id: String,
        model: HeartbeatModel,
        onLoad: () -> Unit,
        onSuccess: (String) -> Unit,
        onError: (String) -> Unit
    ) {
        firestoreClient.storeRequestToFirestore(
            id = id,
            data = model,
            collection = ApiFirebaseResources.HEARTBEAT,
            onLoad = onLoad,
            onSuccess = onSuccess,
            onError = onError
        )
    }

}