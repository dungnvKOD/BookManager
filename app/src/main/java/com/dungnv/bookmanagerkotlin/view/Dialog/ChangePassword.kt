package com.dungnv.myapp.view.Dialog

import android.app.Activity
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseDialog
import com.dungnv.myapp.view.BaseLoginAndRegister
import kotlinx.android.synthetic.main.dialog_change_password.*

class ChangePassword(activity: Activity) : BaseDialog(activity), View.OnClickListener {
    private lateinit var checkPass: CheckPass

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onCreate(savedInstanceState)
        }
        setContentView(R.layout.dialog_change_password)

        checkPass = CheckPass()
        btnOkChangePass.setOnClickListener(this)
        btnHuyChangePass.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val password = edtPassDialog.text.toString()
        val rePassword = edtRePassDialog.text.toString()

        when (v!!.id) {
            R.id.btnOkChangePass -> {
                if (checkPass.checkPassword(edtPassDialog, tilPassDialog) && checkPass.checkRePassword(edtRePassDialog, tilRePassDialog)) {
                    //TODO Dung broadcast de truyen du lieu
                    //chuyendu lieu sang de them vao database


                }
            }
            R.id.btnHuyChangePass -> {
                cancel()
            }
        }
    }

    inner class CheckPass : BaseLoginAndRegister() {
        override fun checkDateBirth(): Boolean {
            return false
        }

        override fun checkPhoneNumber(): Boolean {
            return false
        }


    }
}