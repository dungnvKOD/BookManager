package com.dungnv.myapp.view

import android.support.design.widget.TextInputLayout
import android.widget.EditText

interface ImogenAdminister {//login and register

    fun checkId(editText: EditText, tilEmail: TextInputLayout): Boolean
    fun checkPassword(editText: EditText, tilEmail: TextInputLayout): Boolean
    fun checkRePassword(editText: EditText, tilEmail: TextInputLayout): Boolean
    fun checkPhoneNumber(): Boolean
    fun checkDateBirth(): Boolean

}