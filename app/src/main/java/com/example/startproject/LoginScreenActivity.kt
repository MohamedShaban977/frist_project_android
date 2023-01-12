package com.example.startproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.Toast
import com.example.startproject.core.Validation
import com.example.startproject.widget.BtnLoadingProgressbar
import com.zeugmasolutions.localehelper.Locales
import android.os.Handler
import android.os.Looper
import com.zeugmasolutions.localehelper.currentLocale
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)
        changeLanguage()
        handleDirectionPasswordEditText()



        but_login.setOnClickListener{
                edtxt_email_layout.helperText = Validation().validEmail(  edtxt_email.text.toString(),currentLocale)
                edtxt_password_layout.helperText = Validation().validPassword(edtxt_password.text.toString(),currentLocale)

            if(edtxt_email_layout.helperText == null && edtxt_password_layout.helperText == null){
                Toast.makeText(this,"Success is ${edtxt_email_layout.helperText}${edtxt_password_layout.helperText} ",Toast.LENGTH_LONG).show()
            }
            else {
                Toast.makeText(this,"Error is ${edtxt_email_layout.helperText}${edtxt_password_layout.helperText} ",Toast.LENGTH_LONG).show()

            }


        }



        activity_main_btn.setOnClickListener {
            val progressbar = BtnLoadingProgressbar(it) // `it` is view of button
            progressbar.setLoading()

            Handler(Looper.getMainLooper()).postDelayed({

                progressbar.setState(true){ // executed after animation end
                    Handler(Looper.getMainLooper()).postDelayed({
                        startError(progressbar)
                    },1500)
                }
            }, 2000) // 30

        }
    }

    private fun startError(progressbar: BtnLoadingProgressbar) {
        progressbar.reset()
        Handler(Looper.getMainLooper()).postDelayed({
            progressbar.setLoading()
            Handler(Looper.getMainLooper()).postDelayed({
                progressbar.setState(false){ // executed after animation end
                    Handler(Looper.getMainLooper()).postDelayed({
                        progressbar.reset()
                    },1500)
                }
            },2000)
        }, 600) // 30


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