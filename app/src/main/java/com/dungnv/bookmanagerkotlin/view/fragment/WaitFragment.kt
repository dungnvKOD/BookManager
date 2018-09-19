package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.activity.BackgroundActivity

class WaitFragment : Fragment() {
    private lateinit var rootView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = WaitFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_wait, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        Handler().postDelayed({
            (activity as BackgroundActivity).BaseFragment().replace(LoginRegisterManagerFragment.newFRagment)
        }, 1000)
    }
}