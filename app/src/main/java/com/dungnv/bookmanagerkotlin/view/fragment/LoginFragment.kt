package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.bookmanagerkotlin.common.Key
import com.dungnv.myapp.view.BaseLoginAndRegister
import com.dungnv.myapp.view.activity.BackgroundActivity
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment(), View.OnClickListener {

    private lateinit var login: Login
    private lateinit var rootView: View
    private lateinit var loginBrocast: LoginBrocast
    private lateinit var intenFilter: IntentFilter

    companion object {
        const val TAG = "LoginFragment"
        @SuppressLint("StaticFieldLeak")
        val newFragment = LoginFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login, container, false)
        //khoi tao brocast
        loginBrocast = LoginBrocast()
        intenFilter = IntentFilter(Key.ACTION_1)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //dang ky brocast
        (activity as BackgroundActivity).registerReceiver(loginBrocast, intenFilter)

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
                if (login.checkId(edtEmailL, tilEmailL) && login.checkPassword(edtPassL, tilPassL)) {
                    //TODO  ....
                    (activity as BackgroundActivity).BaseFragment().replace(BackGroundFragment.newFragment)
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

    inner class LoginBrocast : BroadcastReceiver() {
        override fun onReceive(context: Context?, intent: Intent?) {
            Log.d(TAG, "onReceive...")
            when (intent!!.action) {
                Key.ACTION_1 -> {
                    Log.d(TAG, "onReceive  ACTION_1...")
                    //TODO dang loi o broadcast
                    val bundle = intent.extras
                    val pass = bundle.getString(Key.PASS_L)
                    val email = bundle.getString(Key.ID_L)
                    edtEmailL.setText(email)
                    edtPassL.setText(pass)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        (activity as BackgroundActivity).unregisterReceiver(loginBrocast)
    }
}