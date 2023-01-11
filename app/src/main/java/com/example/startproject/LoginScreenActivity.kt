package com.example.startproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zeugmasolutions.localehelper.Locales
import com.zeugmasolutions.localehelper.currentLocale
import kotlinx.android.synthetic.main.activity_login_screen.*

class LoginScreenActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_screen)

but_lang.setOnClickListener{
    if(currentLocale ==Locales.English) {
        updateLocale(Locales.Arabic)
    }
    else {
        updateLocale(Locales.English)
    }
}
    }
}