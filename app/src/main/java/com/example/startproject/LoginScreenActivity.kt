package com.example.startproject

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Editable
import android.text.TextWatcher
import android.view.Gravity
import android.view.MotionEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import com.example.startproject.core.Validation
import com.example.startproject.widget.custom_toast.Constants
import com.example.startproject.widget.custom_toast.CusToast
import com.zeugmasolutions.localehelper.Locales
import com.zeugmasolutions.localehelper.currentLocale
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : BaseActivity(), TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        edtxt_email.addTextChangedListener(this)
        edtxt_password.addTextChangedListener(this)

        changeLanguage()
        handleDirectionPasswordEditText()


/*        but_txt_PrivacyPolicy.setOnClickListener {

            startActivity(Intent(this, WebViewActivity::class.java))
        }*/

        but_login.setOnClickListener {

            but_login.visibility = View.GONE
            ProgressBar.visibility = View.VISIBLE
            loading_screen_login.visibility = View.VISIBLE
            Handler(Looper.getMainLooper()).postDelayed({
                but_login.visibility = View.VISIBLE
                ProgressBar.visibility = View.GONE
                loading_screen_login.visibility = View.GONE

                val intent = Intent(this, HomeScreenActivity::class.java)
                startActivity(intent)

            }, 3000) // 30
//
            edtxt_email_layout.helperText =
                Validation().validEmail(edtxt_email.text.toString(), currentLocale)
            edtxt_password_layout.helperText =
                Validation().validPassword(edtxt_password.text.toString(), currentLocale)

            if (edtxt_email_layout.helperText == null && edtxt_password_layout.helperText == null) {
                CusToast().toastGeneral(this, Constants.TOAST_TYPE_SUCCESS, "Success")
            } else {
                CusToast().toastGeneral(this, Constants.TOAST_TYPE_ERROR, "Error")

            }


        }


/*
        activity_main_btn.setOnClickListener {
            val progressbar = BtnLoadingProgressbar(it) // `it` is view of button
            progressbar.setLoading()

            Handler(Looper.getMainLooper()).postDelayed({

                progressbar.setState(true) { // executed after animation end


                    Handler(Looper.getMainLooper()).postDelayed({
                        startError(progressbar)
                    }, 1500)
                }
            }, 2000) // 30

        }*/


    }

/*
    private fun startError(progressbar: BtnLoadingProgressbar) {
        progressbar.reset()
        Handler(Looper.getMainLooper()).postDelayed({
            progressbar.setLoading()
            Handler(Looper.getMainLooper()).postDelayed({
                progressbar.setState(false) { // executed after animation end
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressbar.reset()
                    }, 1500)
                }
            }, 2000)
        }, 600) // 30


    }*/


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

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
        return super.dispatchTouchEvent(ev)
    }

    override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
        val email = edtxt_email.text.toString()
        val password = edtxt_password.text.toString()
//        but_login.isEnabled = email.trim().isNotEmpty() && password.trim().isNotEmpty()

        println("start : $start \t before : $before \t count : $count \t CharSequence : $s")
        println(edtxt_email.isFocused)
        if (start != 0 && s.toString().isNotEmpty() && email.isNotEmpty()) {
            edtxt_email_layout.helperText = Validation().validEmail(email, currentLocale)
        }

        if (start != 0 && s.toString().isNotEmpty() && password.isNotEmpty()) {
            edtxt_password_layout.helperText = Validation().validPassword(password, currentLocale)
        }
        but_login.isEnabled =
            Validation().validEmail(email) == null && Validation().validPassword(password) == null

        if (!but_login.isEnabled) {
            but_login.setTextColor(Color.DKGRAY)
            but_login.setBackgroundResource(R.drawable.but_disable_style)
        } else {
            but_login.setTextColor(Color.WHITE)
            but_login.setBackgroundResource(R.drawable.but_base_style)

        }

    }


    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
    override fun afterTextChanged(s: Editable?) {}


}