package com.example.startproject.widget.custom_toast

import android.app.Activity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import com.example.startproject.R

class CusToast {

    fun toastGeneral(activity: Activity, toastType: Int = 0, msg: String) {

        val toast: View = activity.layoutInflater.inflate(
            R.layout.layout_toast,
            activity.findViewById(R.id.layout_toast)
        )
        val toastContainer = toast.findViewById<LinearLayout>(R.id.layout_toast)
        val icon = toast.findViewById<ImageView>(R.id.image_icon)
        val textMsg = toast.findViewById<TextView>(R.id.textV_toast)

        when (toastType) {
            Constants.TOAST_TYPE_SUCCESS -> {
                toastContainer.setBackgroundResource(R.drawable.toast_success_background)
                icon.setImageResource(R.drawable.ic_toast_success)
                textMsg.text = msg
            }
            Constants.TOAST_TYPE_WARNING -> {
                toastContainer.setBackgroundResource(R.drawable.toast_warning_background)
                icon.setImageResource(R.drawable.ic_toast_warning)
                textMsg.text = msg
            }
            Constants.TOAST_TYPE_ERROR -> {
                toastContainer.setBackgroundResource(R.drawable.toast_error_background)
                icon.setImageResource(R.drawable.ic_toast_error)
                textMsg.text = msg
            }
            Constants.TOAST_TYPE_GENERAL -> {
                textMsg.text = msg
            }
        }

        with(Toast(activity.applicationContext)) {
            duration = Toast.LENGTH_LONG
            @Suppress("DEPRECATION")
            view = toast
            show()
        }


    }

}