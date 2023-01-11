package com.example.startproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import com.zeugmasolutions.localehelper.Locales
import com.zeugmasolutions.localehelper.currentLocale
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        changeLanguage()
        handleDirectionPasswordEditText()

    }

    private fun changeLanguage() {
        but_lang.setOnClickListener {
            if (currentLocale == Locales.English) {
                updateLocale(Locales.Arabic)
            } else {
                updateLocale(Locales.English)
            }
        }
    }

    @SuppressLint("RtlHardcoded")
    private fun handleDirectionPasswordEditText() {
        if (currentLocale == Locales.Arabic) {
            edtxt_password.gravity = Gravity.RIGHT or Gravity.CENTER_VERTICAL
            edtxt_password.textDirection = View.TEXT_DIRECTION_RTL

        } else {
            edtxt_password.gravity = Gravity.LEFT or Gravity.CENTER_VERTICAL
            edtxt_password.textDirection = View.TEXT_DIRECTION_LTR

        }
    }


}