package com.dungnv.myapp.view

import android.support.design.widget.TextInputLayout
import android.widget.EditText

abstract class BaseLoginAndRegister : ImogenAdminister {

    private lateinit var pass: String

    /**
     *  lop lay tac dung la check email va pass de cho login va register dung luon 3  phuong thuc nay.
     *  @BaseLoginAndRegister impliment tu ImogenAdminister la 1 interface
     *
     */

    override fun checkId(editText: EditText, tilEmail: TextInputLayout): Boolean {
        /**
         * @param editText tham số thứ nhất là 1 EditText của Email
         * @param tilEmail tham số thứ 2 là TextInputLayout bao thằng EditText
         *
         * @return true nếu email khác null, đúng định dạng
         * @return false ngược lại
         */

        val pattern: String? = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        return if (editText.text!!.toString() == "") {
            tilEmail.isErrorEnabled = true
            tilEmail.error = "Not null!"
            false
        } else {
            if (!editText.text.toString().trim().matches(pattern!!.toRegex())) {
                tilEmail.isErrorEnabled = true
                tilEmail.error = "Enter a Valid Email..."
                false
            } else {
                tilEmail.isErrorEnabled = false
                true
            }
        }
    }


    override fun checkRePassword(editText: EditText, tilPass: TextInputLayout): Boolean {

        /**
         * @param editText tham số thứ nhất là 1 EditText của Pas
         * @param tilEmail tham số thứ 2 là TextInputLayout bao thằng EditText
         *
         * @return true nếu pass trùng với pass đã nhập
         * @return false ngược lại
         */

        return if (pass == editText.text.toString()) {
            tilPass.isErrorEnabled = false
            true
        } else {
            tilPass.isErrorEnabled = true
            tilPass.error = "mat khau khong khop !"
            false
        }
    }

    override fun checkPassword(editText: EditText, tilEmail: TextInputLayout): Boolean {

        /**
         * @param editText tham số thứ nhất là 1 EditText của Email
         * @param tilEmail tham số thứ 2 là TextInputLayout bao thằng EditText
         *
         * @return true nếu pas khác null, lớn hơn hoặc bằng 6 ký tự
         * @return false ngược lại
         */
        pass = editText.text.toString()
        if (editText.text!!.toString() == "") {
            tilEmail.isErrorEnabled = true
            tilEmail.error = "Not null"
            return false
        } else {
            return if (editText.text.toString().trim().length <= 6) {
                tilEmail.isErrorEnabled = true
                tilEmail.error = "Password should be Greater than 6"
                false
            } else {
                //                if (){ TODO kiem tra mat khau dang Dung1995
                //
                //                }else{
                //
                //                }
                tilEmail.isErrorEnabled = false
                true

            }
        }
    }
}
