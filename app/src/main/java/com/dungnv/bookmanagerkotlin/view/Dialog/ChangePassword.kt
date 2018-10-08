package com.dungnv.myapp.view.Dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Key
import com.dungnv.myapp.view.BaseDialog
import com.dungnv.myapp.view.BaseLoginAndRegister
import com.dungnv.myapp.view.activity.BackgroundActivity
import kotlinx.android.synthetic.main.dialog_change_password.*

class ChangePassword(val activity: Activity) : BaseDialog(activity), View.OnClickListener {
    private lateinit var checkPass: CheckPass

    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            super.onCreate(savedInstanceState)
        }
        setContentView(R.layout.dialog_change_password)

        checkPass = CheckPass()

        (activity as BackgroundActivity).setSupportActionBar(toober_change_dialog)
        activity .supportActionBar!!.title = "Chi tiet"

        val user = activity.user
        txtPhoneS.text = user!!.phoneNumber
        txtNameS.text = user!!.name
        edtPhoneS.setText(user.phoneNumber)
        edtNameS.setText(user.name)
        activity .user = null

        btnSaveS.setOnClickListener(this)
        btnCancelS.setOnClickListener(this)
        btnNameS.setOnClickListener(this)
        btnPhoneS.setOnClickListener(this)

    }


    override fun onClick(v: View?) {
        val password = edtPassS.text.toString().trim()
        val rePassword = edtRePassS.text.toString().trim()
        val name = edtNameS.text.toString().trim()
        val phone = edtPhoneS.text.toString().trim()


        when (v!!.id) {
            R.id.btnSaveS -> {
                if (checkPass.checkPassword(edtPassS, tilPassS) && checkPass.checkRePassword(edtRePassS, tilRePassS)) {
                    //TODO Dung broadcast de truyen du lieu
                    //chuyendu lieu sang de them vao database

                    val intent = Intent(Key.ACTION_S)
                    val bundel = Bundle()
                    bundel.putString(Key.NAME_S, name)
                    bundel.putString(Key.PASS_S, password)
                    bundel.putString(Key.PHONE_S, phone)
                    intent.putExtras(bundel)
                    activity.sendBroadcast(intent)
                    cancel()
                }
            }

            R.id.btnNameS -> {
                edtNameS.visibility = View.VISIBLE
                txtNameS.visibility = View.INVISIBLE
            }
            R.id.btnPhoneS -> {
                edtPhoneS.visibility = View.VISIBLE
                txtPhoneS.visibility = View.INVISIBLE
            }
            R.id.btnCancelS -> {
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