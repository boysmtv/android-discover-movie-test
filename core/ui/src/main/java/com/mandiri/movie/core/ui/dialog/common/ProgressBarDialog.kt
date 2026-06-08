/*
 * Project: Mandiri Test Movie
 * Author: Boys.mtv@gmail.com
 * File: ProgressBarDialog.kt
 *
 * Last modified by Dedy Wijaya on 26/06/08 18.45
 */

package com.mandiri.movie.core.ui.dialog.common

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.mandiri.movie.core.ui.databinding.DialogProgressbarBinding
import com.mandiri.movie.core.utilities.Constant.ONE_THOUSAND_FIFTY
import androidx.core.graphics.drawable.toDrawable

class ProgressBarDialog : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return DialogProgressbarBinding.inflate(inflater, container, false).root
    }

    override fun onStart() {
        super.onStart()
        dialog?.setCancelable(false)
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT)
        dialog?.window?.setBackgroundDrawable(Color.GRAY.toDrawable().apply { alpha = ONE_THOUSAND_FIFTY })
    }

}