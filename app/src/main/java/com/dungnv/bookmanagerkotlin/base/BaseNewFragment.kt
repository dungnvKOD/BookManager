package com.dungnv.myapp.view

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import com.dungnv.bookmanagerkotlin.R

abstract class BaseNewFragment(private val fragmentManager: FragmentManager, val idView: Int) {

    fun addFragment(f: Fragment) {
        val tag = f.javaClass.name
        val fm = fragmentManager
        var fragment = fm.findFragmentByTag(tag)

        if (fragment != null) {
            val frms: ArrayList<Fragment> = fm.fragments as ArrayList<Fragment>
            for (frm: Fragment in frms) {
                fm.beginTransaction()
                        .hide(frm)
                        .commit()
            }

            fm.beginTransaction()
                    .show(f)
                    .commit()
        } else {
            fragment = f
            fm.beginTransaction()
                    .setCustomAnimations(
                            R.anim.left_enter,
                            R.anim.left_exit,
                            R.anim.right_enter,
                            R.anim.right_exit
                    )
                    .add(idView, fragment, tag)
                    .addToBackStack(null)
                    .commit()
        }
    }

    fun popbacktask() {
        fragmentManager.popBackStack()
    }

    fun replace(f: Fragment) {

        fragmentManager.beginTransaction()
                .setCustomAnimations(
                        R.anim.left_enter,
                        R.anim.left_exit,
                        R.anim.right_enter,
                        R.anim.right_exit
                )
                .replace(idView, f)
                .commit()
    }

}