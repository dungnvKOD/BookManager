package com.dungnv.myapp.view.fragment.menufragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.dungnv.bookmanagerkotlin.R
import com.dungnv.myapp.view.activity.BackgroundActivity
import com.dungnv.myapp.view.fragment.BackGroundFragment
import kotlinx.android.synthetic.main.fragment_the_loai.*
import kotlinx.android.synthetic.main.fragment_thong_ke.*

class ThongKeFragment : Fragment() {
    private lateinit var rootView: View

    companion object {
        val newFragment = ThongKeFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_thong_ke, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as BackgroundActivity).setSupportActionBar(toobarThongKe)
        (activity as BackgroundActivity).supportActionBar!!.setDisplayShowTitleEnabled(false)
        toobarThongKe.navigationIcon = (activity as BackgroundActivity).resources.getDrawable(R.drawable.ic_arrow_back_black_24dp, (activity as BackgroundActivity).theme)
        toobarThongKe.setNavigationOnClickListener {
            BackGroundFragment().BaseFragment().popbacktask()

        }
    }

}