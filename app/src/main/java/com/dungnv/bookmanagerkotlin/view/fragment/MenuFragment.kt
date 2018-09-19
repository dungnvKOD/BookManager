package com.dungnv.myapp.view.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.fragment.menufragment.*
import kotlinx.android.synthetic.main.fragment_menu.*

class MenuFragment : Fragment(), View.OnClickListener {

    private lateinit var rootView: View

    companion object {
        @SuppressLint("StaticFieldLeak")
        val newFragment = MenuFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_menu, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        //dang ky
        btnUser.setOnClickListener(this)
        btnTheLoai.setOnClickListener(this)
        btnBook.setOnClickListener(this)
        btnBill.setOnClickListener(this)
        btnThongKe.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v!!.id) {

            R.id.btnUser -> {
                val userFragment = UserFragment.newFragment
                userFragment.show(childFragmentManager, userFragment.javaClass.name)
            }
            R.id.btnTheLoai -> {
                BackGroundFragment().BaseFragment().addFragment(TheLoaiFragment.newFragment)
            }
            R.id.btnBook -> {
                BackGroundFragment().BaseFragment().addFragment(BookFragment.newFragment)
            }
            R.id.btnBill -> {
                BackGroundFragment().BaseFragment().addFragment(BillFragment.newFragment)
            }
            R.id.btnThongKe -> {
                BackGroundFragment().BaseFragment().addFragment(ThongKeFragment.newFragment)

            }
        }
    }
}