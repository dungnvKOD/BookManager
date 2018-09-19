package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.BaseNewFragment

class BackGroundFragment : Fragment() {
    private lateinit var rootView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = BackGroundFragment()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_background, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //TODO Fragment menu luon hien

        childFragmentManager.beginTransaction()
                .add(R.id.frameLayoutMenu, MenuFragment.newFragment, tag)
                .commit()
    }

    inner class BaseFragment : BaseNewFragment(BackGroundFragment.newFragment.childFragmentManager, R.id.frameLayoutMenu)

}