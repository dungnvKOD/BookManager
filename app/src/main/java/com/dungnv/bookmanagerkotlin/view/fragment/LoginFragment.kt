package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Constants
import com.dungnv.myapp.model.data.UserData
import com.dungnv.myapp.view.BaseLoginAndRegister
import com.dungnv.myapp.view.activity.BackgroundActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var login: Login
    private lateinit var rootView: View
    private lateinit var userData: UserData

    companion object {
        const val TAG = "LoginFragment"
        @SuppressLint("StaticFieldLeak")
        val newFragment = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        userData = UserData(activity!!)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        login = Login()
        init()
    }

    private fun init() {
        btnRegisterL.setOnClickListener(this)
        btnLoginL.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnLoginL -> {
                val email: String? = edtEmailL.text.toString().trim()
                val pass: String? = edtPassL.text.toString().trim()
//                Log.d(TAG, "$pass...")
//                Log.d(TAG, "${userData.getUserByEmail(email)!!.password}...")

                if (login.checkId(edtEmailL, tilEmailL) && login.checkPassword(edtPassL, tilPassL)) {
                    //TODO  ....
                    if (userData.getUserByEmail(email!!) != null) {
                        if (pass == userData.getUserByEmail(email)!!.password) {
//                            (activity as BackgroundActivity).myUser = userData.getUserByEmail(email)

                            (activity as BackgroundActivity).email = userData.getUserByEmail(email)!!.userName
                            (activity as BackgroundActivity).pass = userData.getUserByEmail(email)!!.password
                            (activity as BackgroundActivity).phone = userData.getUserByEmail(email)!!.phoneNumber
                            (activity as BackgroundActivity).name = userData.getUserByEmail(email)!!.name

                            if (Constants.isDebug) Log.d(TAG, "...DUNG...: ${userData.getUserByEmail(email)!!.name}")
                            (activity as BackgroundActivity).BaseFragment().replace(BackGroundFragment.newFragment)
                        }
                    } else {
                        Toast.makeText(activity, "Sai mat khau hoac tai khoan", Toast.LENGTH_LONG).show()
                    }
                }
            }
            R.id.btnRegisterL -> {
                LoginRegisterManagerFragment().BaseFragment().addFragment(RegisterFragment.newFragment)
            }
        }
    }

    internal class Login : BaseLoginAndRegister() {
        override fun checkPhoneNumber(): Boolean {
            return false
        }

        override fun checkDateBirth(): Boolean {
            return false
        }
    }


}