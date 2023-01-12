package com.example.startproject.core

class RegEx {

   val rxPassword:Regex = Regex("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#\$&*~]).{8,16}$")



}