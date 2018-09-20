package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Key
import com.dungnv.myapp.model.Entity.User
import com.dungnv.myapp.model.data.UserData
import com.dungnv.myapp.view.BaseLoginAndRegister
import com.dungnv.myapp.view.activity.BackgroundActivity
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment(), View.OnClickListener {
    private lateinit var rootView: View
    private lateinit var register: Register
    private lateinit var usersData: UserData

    companion object {
        @SuppressLint("StaticFieldLeak")
        val TAG = "RegisterFragment"
        val newFragment = RegisterFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_register, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        usersData = UserData(activity!!)
        init()
    }

    private fun init() {
        register = Register()
        backRegister()
        //dang ky
        btnRegisterR.setOnClickListener(this)
    }

    //back register
    @SuppressLint("PrivateResource")
    private fun backRegister() {
        (activity as BackgroundActivity).setSupportActionBar(toobar_register)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobar_register.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(
                R.drawable.ic_arrow_back_black_24dp,
                (activity as BackgroundActivity).theme)

        toobar_register.setNavigationOnClickListener {
            LoginRegisterManagerFragment().BaseFragment().popbacktask()
        }
    }

    //su kien ckick
    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnRegisterR -> {
                register()
            }
        }
    }

    //TODO su kien dang ky
    private fun register() {
        if (register.checkId(edtEmailR,
                        tilEmailR)
                && register.checkPassword(edtPassR, tilPassR)
                && register.checkPhoneNumber()
                && register.checkDateBirth()) {
            val pass = edtPassR.text.toString().trim()
            val email = edtEmailR.text.toString().trim()
            val name = edtNameR.text.toString().trim()
            val phoneNumber = edtPhoneNumberR.text.toString().trim()
            val user = User(email, pass, name, phoneNumber)

            //TODO dang ky thanh cong va xu ly data base
            if (usersData.checkRegister(email)) {
                val check = usersData.insertUser(user)

                if (check) {
                    (activity as BackgroundActivity).user = usersData.getUserByEmail(email)
                    (activity as BackgroundActivity).BaseFragment().replace(BackGroundFragment.newFragment)
                } else {
                    Toast.makeText(activity!!, "Them khong thanh cong", Toast.LENGTH_LONG).show()
                }
            } else {
                Toast.makeText(activity!!, "Da co tai khoan", Toast.LENGTH_LONG).show()
            }

        } else {
            //TODO dang ky khong thnah cong
        }
    }

    inner class Register : BaseLoginAndRegister() {

        override fun checkPhoneNumber(): Boolean {
            val phoneNumber = edtPhoneNumberR.text!!.toString()
            return if (phoneNumber == "") {
                tilPhoneNumberR.isErrorEnabled = true
                tilPhoneNumberR.error = "phone number not null"
                false
            } else {
                if (phoneNumber.length in 10..11) {
                    tilPhoneNumberR.isErrorEnabled = false
                    true
                } else {
                    tilPhoneNumberR.isErrorEnabled = true
                    tilPhoneNumberR.error = " so dien thoai 10 hoac 11 so"
                    false
                }
            }
        }

        override fun checkDateBirth(): Boolean {
            return true
        }

    }
}