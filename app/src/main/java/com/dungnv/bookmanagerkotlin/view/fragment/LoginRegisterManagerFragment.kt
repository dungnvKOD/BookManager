package com.dungnv.myapp.view.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseNewFragment

class LoginRegisterManagerFragment : Fragment() {
    private lateinit var rootView: View
    private lateinit var baseFragment: BaseFragment

    companion object {
        val newFRagment: Fragment = LoginRegisterManagerFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_login_register, container, false)
        baseFragment = BaseFragment()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        baseFragment.replace(LoginFragment.newFragment)

    }
    //phai chi ro la fragment nao khong la loi TODO fargment not been attached ye
    inner class BaseFragment : BaseNewFragment(LoginRegisterManagerFragment.newFRagment.childFragmentManager, R.id.frameLayoutLoginAndRegister)
}