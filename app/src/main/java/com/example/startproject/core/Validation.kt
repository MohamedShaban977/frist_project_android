package com.example.startproject.core

import android.util.Patterns
import com.zeugmasolutions.localehelper.Locales
import java.util.*


class Validation {
    fun validEmail(email: String): String? {
        if(email.isEmpty()) return "Email Address is empty"

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) return "Invalid Email Address (mail@gmail.com)"

        return null
    }
    fun validEmail(email: String, currentLocale: Locale): String? {

        if (email.isEmpty()) {
            return if (currentLocale == Locales.English) "Email Address is empty"
            else "البريد الالكترونى فارع"
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return if (currentLocale == Locales.English) "Invalid Email Address (mail@gmail.com)"
            else "(mail@gmail.com) الرجاء إدخال البريد الإلكتروني بشكل صحيح"
        }
        return null
    }



    fun validPassword(password: String): String? {
        if (password.isEmpty()) {
            return "password is empty"
        } else if (!password.matches(RegEx().rxPassword)) {
            return "Invalid Password"
        }
        return null
    }
    fun validPassword(password: String ,currentLocale: Locale): String? {
//        if(password.length < 8) return "Minimum 8 Character Password"
//
//        if(!password.matches(".*[A-Z].*".toRegex())) return "Must Contain 1 Upper-case Character"
//
//        if(!password.matches(".*[a-z].*".toRegex())) return "Must Contain 1 Lower-case Character"
//
//        if(!password.matches(".*[@#\$%^&+=].*".toRegex())) return "Must Contain 1 Special Character (@#\$%^&+=)"
//
//
//        return null

        if (password.isEmpty()) {
            return if (currentLocale == Locales.English) "password is empty"
            else "كلمة المرور فارغة"
        }
        else if (!password.matches(RegEx().rxPassword)) {
            return if (currentLocale == Locales.English) "Invalid Password"
            else "كلمة المرور غير مطابقة للمواصفات"
        }
        return null
    }

}