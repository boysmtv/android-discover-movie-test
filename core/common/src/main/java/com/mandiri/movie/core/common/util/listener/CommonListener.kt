/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: CommonListener.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.common.util.listener

interface CommonListener {

    fun restartTask()

    fun mainErrorHandler(
        code: String?,
        title: String? = null,
        message: String? = null,
        httpCode: String? = null
    )

    fun showProgressDialog(isShow: Boolean)
}